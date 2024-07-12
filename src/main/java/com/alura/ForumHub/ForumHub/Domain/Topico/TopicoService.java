package com.alura.ForumHub.ForumHub.Domain.Topico;


import com.alura.ForumHub.ForumHub.Domain.Usuario.UsuarioRepository;
import com.alura.ForumHub.ForumHub.Infra.Security.TokenService;
import com.alura.ForumHub.ForumHub.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private  TopicoRepository topicoRepository;


    public Topico autenticarAutor(String token, Long id) {
        var email = tokenService.getSubject(token.replace("Bearer ", ""));
        var autor = usuarioRepository.findByEmail(email);
        var topico = topicoRepository.getReferenceById(id);



        if(autor != topico.getAutor()){
            throw new ValidacaoException("Voce nao possui autorizacao para alterar, modificar ou deletar esse topico");
        }

        return topico;

    }

}
