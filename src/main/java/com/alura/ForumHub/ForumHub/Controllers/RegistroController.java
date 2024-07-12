package com.alura.ForumHub.ForumHub.Controllers;

import com.alura.ForumHub.ForumHub.Domain.Usuario.AutenticacaoDTO;
import com.alura.ForumHub.ForumHub.Domain.Usuario.RegistroDTO;
import com.alura.ForumHub.ForumHub.Domain.Usuario.UserService;
import com.alura.ForumHub.ForumHub.Domain.Usuario.Usuario;
import com.alura.ForumHub.ForumHub.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cadastrar")
public class RegistroController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> registrarNovoUsuario(@RequestBody @Valid AutenticacaoDTO dados) {
        try {
            Usuario newUser = userService.cadastrarNovoUsuario(dados);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RegistroDTO(newUser));
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}