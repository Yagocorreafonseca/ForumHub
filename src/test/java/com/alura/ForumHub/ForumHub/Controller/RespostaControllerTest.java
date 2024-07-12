package com.alura.ForumHub.ForumHub.Controller;

import com.alura.ForumHub.ForumHub.Domain.Resposta.*;
import com.alura.ForumHub.ForumHub.Domain.Topico.TopicoRepository;
import com.alura.ForumHub.ForumHub.Domain.Usuario.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class RespostaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<CadastroRespostaDTO> dadosCadastroRespostaJson;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @MockBean
    private RespostaRespository respository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    @DisplayName("Deveria devolver codito http 400 quando as informacoes estao invalidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        var response = mvc
                .perform(post("/respostas"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codito http 201 quando informacoes estao validas")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        var topico = topicoRepository.getReferenceById(1L);

        var autor = usuarioRepository.getReferenceByEmail("joao.pedro@example.com");

        var dadosCadastro = new CadastroRespostaDTO(
                "Ricardo", 30l, "Sugestão de resposta"
        );
        var resposta = new Resposta(null, dadosCadastro.nome(), dadosCadastro.mensagem(), topico, LocalDateTime.now(), autor, StatusResposta.ABERTO);

        when(respository.save(any())).thenReturn(resposta);


        String token = "Replace with a valid mock token";

        var response = mvc.perform(post("/respostas")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroRespostaJson.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        var dadosDetalhadamento = new DetalhamentoRespostaDTO(resposta);



        DetalhamentoRespostaDTO respostaAtual = objectMapper.readValue(response.getContentAsString(), DetalhamentoRespostaDTO.class);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(respostaAtual.id()).isEqualTo(dadosDetalhadamento.id());
        assertThat(respostaAtual.nome()).isEqualTo(dadosDetalhadamento.nome());
        assertThat(respostaAtual.autor_Id()).isEqualTo(dadosDetalhadamento.autor_Id());
        assertThat(respostaAtual.idTopico()).isEqualTo(dadosDetalhadamento.idTopico());
        assertThat(respostaAtual.sugestao()).isEqualTo(dadosDetalhadamento.sugestao());
        assertThat(respostaAtual.status()).isEqualTo(dadosDetalhadamento.status());

    }
}

