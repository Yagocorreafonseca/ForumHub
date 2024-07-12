package com.alura.ForumHub.ForumHub.Domain.Topico;


public record AtualizacaoTopicoDTO(
        String mensagem,
        String titulo) {
    public AtualizacaoTopicoDTO(Topico topico){
        this(topico.getMensagem(), topico.getTitulo());
    }
}
