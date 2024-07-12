package com.alura.ForumHub.ForumHub.Infra.Security;

import com.alura.ForumHub.ForumHub.Domain.Usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("{api.security.token.secret}")
    private String secret;

    public String criarToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("forumhub")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(tempoExpiracao())
                    .sign(algorithm);


        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT!", exception);
        }
    }

    private Instant tempoExpiracao() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-3"));
    }


    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("forumhub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Erro ao verificar token JWT!", exception);
        }
    }

}
