package com.alura.ForumHub.ForumHub.Domain.Curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Curso")
@Table(name = "cursos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Curso(CadastroCursosDTO dados) {
        this(null, dados.nome(), dados.categoria());
    }

    public void atualizarInformacoes(AtualizarCursosDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();

        }
        if (dados.categoria() != null) {
            this.categoria = dados.categoria();
        }

    }
}

