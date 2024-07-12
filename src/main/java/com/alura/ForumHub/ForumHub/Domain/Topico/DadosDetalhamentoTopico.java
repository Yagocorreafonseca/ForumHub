package com.alura.ForumHub.ForumHub.Domain.Topico;

import java.time.LocalDateTime;


public record DetalhadamentoTopicoDTO(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao
) {
    public DetalhadamentoTopicoDTO(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao());
    }
}
