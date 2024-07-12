package com.alura.ForumHub.ForumHub.Domain.Curso;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CadastroCursosDTO(
        @JsonAlias("nomeCurso")
        @NotBlank
        String nome,
        @NotNull
        Categoria categoria

) {
}
