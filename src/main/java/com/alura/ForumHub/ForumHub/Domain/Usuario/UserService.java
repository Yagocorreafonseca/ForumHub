package com.alura.ForumHub.ForumHub.Domain.Usuario;


import com.alura.ForumHub.ForumHub.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario cadastrarNovoUsuario(AutenticacaoDTO dados) {
        if (repository.existsByEmail(dados.email())) {
            throw new ValidacaoException("Email já cadastrado no sistema.");
        }


        Usuario user = new Usuario();

        user.setEmail(dados.email());
        user.setSenha(passwordEncoder.encode(dados.senha()));

        return repository.save(user);
    }
}
