package com.alura.ForumHub.ForumHub.Domain.Topico;

import com.alura.ForumHub.ForumHub.Domain.Curso.Curso;
import com.alura.ForumHub.ForumHub.Domain.Resposta.Resposta;
import com.alura.ForumHub.ForumHub.Domain.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    private EstadoDoTopico estadoDoTopico;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarios_id")
    private Usuario autor;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Resposta> resposta;


    public void setResposta(List<Resposta> resposta) {
        resposta.forEach(r -> r.setTopico(this));
        this.resposta = resposta;
    }


    public void atualizarInformacoes(CadastroTopicoDTO dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }

    }


}


