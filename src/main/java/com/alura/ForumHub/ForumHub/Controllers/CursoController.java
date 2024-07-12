package com.alura.ForumHub.ForumHub.Controllers;

import com.alura.ForumHub.ForumHub.Domain.Curso.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoCursoDTO> criarCurso(@RequestBody @Valid CadastroCursosDTO dados, UriComponentsBuilder uriBuilder) {
        var curso = new Curso(dados);
        repository.save(curso);
        var uri = uriBuilder.path("cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoCursoDTO(curso));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhamentoCursoDTO> atualizarCurso(@RequestBody @Valid AtualizarCursosDTO dados) {
        var curso = repository.getReferenceById(dados.id());
        curso.atualizarInformacoes(dados);
        return ResponseEntity.ok().body(new DetalhamentoCursoDTO(curso));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhamentoCursoDTO>> listarCursos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DetalhamentoCursoDTO::new);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirCurso(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}

