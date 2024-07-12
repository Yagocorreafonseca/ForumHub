package com.alura.ForumHub.ForumHub.Controllers;

import com.alura.ForumHub.ForumHub.Domain.Usuario.AutenticacaoDTO;
import com.alura.ForumHub.ForumHub.Domain.Usuario.Usuario;
import com.alura.ForumHub.ForumHub.Infra.Security.DadosTokenJWT;
import com.alura.ForumHub.ForumHub.Infra.Security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efeituarLogin(@RequestBody @Valid AutenticacaoDTO dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(token);
        var tokenJWT = tokenService.criarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }


}