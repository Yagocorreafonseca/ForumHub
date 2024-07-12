package com.alura.ForumHub.ForumHub.Controller;

import com.alura.ForumHub.ForumHub.Domain.Curso.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CursoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<CadastroCursosDTO> dadosCadastroCursosJason;

    @Autowired
    private  JacksonTester<DetalhamentoCursoDTO> dadosDetalhadamentoCursoJason;

    @MockBean
    private CursoRepository repository;


    @Test
    @DisplayName("Deveria devolver codigo 400 quando informacoes estao invalidas")
    @WithMockUser
    void curso_cenario1() throws Exception {
        var response = mvc.perform(post("/cursos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName("Deveria devolver codigo 200 quando informacoes estao correta")
    @WithMockUser
    void curso_cenario2() throws Exception{
        var categoria = Categoria.DESIGN;
        var dadosCadastro = new CadastroCursosDTO("Design de UX", categoria);

        when(repository.save(any())).thenReturn(new Curso(dadosCadastro));

        var response = mvc
                .perform(post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroCursosJason.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        var dadosDetalhamento = new DetalhamentoCursoDTO(null,dadosCadastro.nome(), dadosCadastro.categoria());

        var jsonEsperado = dadosDetalhadamentoCursoJason.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);


    }


}

