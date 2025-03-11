package com.barber.apiBarber.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.barber.apiBarber.model.User;
import com.barber.apiBarber.Repository.UserRepository;
import com.barber.apiBarber.dtos.AuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TokenService {

    @Autowired
    private UserRepository usuarioRepository;

    public String obterToken(AuthDTO authDTO){
        var user = usuarioRepository.findByEmail(authDTO.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário inexistente."));
        return gerarToken(user);
    }

    public String obterLogin(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.require(algorithm)
                    .withIssuer("api-barber")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTCreationException e){
            throw new RuntimeException(e);
        }
    }

    private String gerarToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withIssuer("api-barber")
                    .withSubject(user.getEmail())
                    .withClaim("roles", user.getRole().name())
                    .withExpiresAt(Instant.now().plus(8L, ChronoUnit.HOURS))
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }

}
