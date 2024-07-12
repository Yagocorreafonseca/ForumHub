package com.alura.ForumHub.ForumHub.Domain.Resposta;

import java.time.LocalDateTime;

public record DetalhamentoRespostaDTO(
        Long id,
        String nome,
        Long autor_Id,
        Long idTopico,
        LocalDateTime dataCriacao,
        String sugestao,
        StatusResposta status

) {

    public DetalhamentoRespostaDTO(Resposta resposta){
        this(resposta.getId(), resposta.getNome(), resposta.getAutor().getId(),resposta.getTopico().getId(), resposta.getDataCriacao(), resposta.getMensagem(), resposta.getStatus());
    }
}

