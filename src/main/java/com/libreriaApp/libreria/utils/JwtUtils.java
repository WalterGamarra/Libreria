package com.libreriaApp.libreria.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.AlgorithmConstraints;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {


    //Configurar la autenticidad del token
    @Value("${security.jwt.private.key}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;


    //Creacion de tokens mediainte metodos con Authentication de spring security
    public String createToken(Authentication authentication){

        Algorithm algorithm = Algorithm.HMAC256(privateKey);

        //Queda en el context holder
        String userName = authentication.getPrincipal().toString();

        //Convertir getAturities en stream
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String jwToken = JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(userName)
                .withClaim("authorities", authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+1800000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);

        return jwToken;
    }

    //Decodificar y validar nuestros tokens
    public DecodedJWT validateToken(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();

            //Si no genera exception
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;

        }

        catch (JWTVerificationException exception){
            throw new JWTVerificationException("Invalid token. Not authorized");
        }

    }

    //Metodo para obtner usuario
    public String extractUserName(DecodedJWT decodedJWT){
        return decodedJWT.getSubject().toString();
    }

    //Metodo para obtener Claim en particular
    public Claim getSpecificClaim (DecodedJWT decodedJWT, String clainname){
        return decodedJWT.getClaim(clainname);
    }

    //Metodo para obtener todos los Claims
    public Map<String,Claim> returnAllClaims (DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }
}
