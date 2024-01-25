package com.system.springboot.Infra;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.system.springboot.User.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;



//SERVIÇO RESPONSAVEL POR CRIAR O TOKEN
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    public String TokenGenerator(User user){
        try { //RESPONSAVEL POR CRIAR UM TOKEN PARA RETORNAR PARA USUARIO
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("UserAPI")//NOME DA API
                    .withSubject(user.getUsername())//NOME DO USUARIO
                    .withExpiresAt(time())//DEFINE O TEMPO QUE O TOKEN SERÁ VALIDO, 2 HORAS
                    .sign(algorithm);//RETORNA
        } catch (JWTCreationException exception){
            throw new RuntimeException("Token Error");
        }
    }

    public String getSubject(String token) {//PEGA O NOME DO USUARIO COM BASE NO TOKEN
        try {
//            System.out.println("TOKEN: " + token);
//            System.out.println("Secret: " + secret);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("UserAPI")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getCause());
        }

    }

    private Instant time() {
    return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.UTC);
    }//RETORNA 4 HORAS A MAIS DO QUE A HORA ATUAL
}
