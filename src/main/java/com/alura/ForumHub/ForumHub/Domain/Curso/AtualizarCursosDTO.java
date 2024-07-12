package com.alura.ForumHub.ForumHub.Domain.Curso;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;


public record AtualizarCursosDTO(
        @NotNull
        Long id,
        @JsonAlias("nomeCurso")
        String nome,
        Categoria categoria
) {
}
