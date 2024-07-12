package com.alura.ForumHub.ForumHub.Domain.Resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroRespostaDTO(
        @NotBlank
        String nome,
        @NotNull
        Long idTopico,
        @NotBlank
        String mensagem
) {
}
