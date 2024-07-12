package com.alura.ForumHub.ForumHub.Domain.Topico;

import com.alura.ForumHub.ForumHub.Domain.Resposta.ListarRespostasDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ListarTopicosDTO(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime data,
        EstadoDoTopico estadoDoTopico,
        List<ListarRespostasDTO> respostas
) {
    public ListarTopicosDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getEstadoDoTopico(),topico.getResposta().stream().map(ListarRespostasDTO::new).collect(Collectors.toUnmodifiableList()));
    }
}
