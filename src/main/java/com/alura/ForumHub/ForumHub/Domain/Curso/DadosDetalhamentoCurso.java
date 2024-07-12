package com.alura.ForumHub.ForumHub.Domain.Curso;


public record DetalhamentoCursoDTO(
        Long id,
        String nome,
        Categoria categoria

) {
    public DetalhamentoCursoDTO(Curso curso) {this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
