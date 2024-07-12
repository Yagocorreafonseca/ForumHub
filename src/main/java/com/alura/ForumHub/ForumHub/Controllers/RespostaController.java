package com.alura.ForumHub.ForumHub.Controllers;

import com.alura.ForumHub.ForumHub.Domain.Resposta.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @Autowired
    private RespostaRespository respostaRespository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoRespostaDTO> cadastrarResposta(@RequestHeader("Authorization") String token, @RequestBody @Valid CadastroRespostaDTO dados, UriComponentsBuilder uriBuilder) {
        var autor = respostaService.EncontrarNomeAutor(token, dados.nome());
        var topico = respostaService.localizarTopico(dados.idTopico());
        var resposta = new Resposta(null, dados.nome(), dados.mensagem(), topico, LocalDateTime.now(), autor, StatusResposta.ABERTO);
        respostaRespository.save(resposta);
        var uri = uriBuilder.path("/respostas/{id}").buildAndExpand(resposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoRespostaDTO(resposta));

    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhamentoRespostaDTO> atualizarResposta(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody @Valid AtualizacaoRespostaDTO dados) {
        var resposta = respostaService.autenticarAutor(token, id);
        resposta.autualizarInformacoes(dados);

        return ResponseEntity.ok().body(new DetalhamentoRespostaDTO(resposta));
    }

    @GetMapping
    public ResponseEntity<Page<ListarRespostasDTO>> listarRespostas(@PageableDefault(size = 10, sort = {"status"}) Pageable paginacao) {
        var page = respostaRespository.findAll(paginacao).map(ListarRespostasDTO::new);

        return ResponseEntity.ok(page);


    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletarResposta(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        respostaService.deletarResposta(token, id);
        return ResponseEntity.noContent().build();
    }



}

