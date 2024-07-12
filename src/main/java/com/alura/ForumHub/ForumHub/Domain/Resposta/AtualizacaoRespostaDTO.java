package com.alura.ForumHub.ForumHub.Domain.Resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoRespostaDTO(
        @NotBlank
        String mensagem,
        @NotNull
        StatusResposta status
) {
}
