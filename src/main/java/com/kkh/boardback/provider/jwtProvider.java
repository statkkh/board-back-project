package com.kkh.boardback.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
    
    @Value("${secret-key }")
    private String secretkey;

    public String create(String email){
        Date expiration = Date.from(Instant.now().plus(5, ChronoUnit.HOURS));
        String jwt = Jwts.builder()
                                .signWith(SignatureAlgorithm.HS256, secretkey)
                                .setSubject(email).setIssuedAt(new Date()).setExpiration(expiration)
                                .compact();

        return jwt;
    }

    public String validate(String jwt){
        String email = jwt;
        try {

            Claims claims = Jwts.parser()
                                .setSigningKey(secretkey)
                                .parseClaimsJws(jwt)
                                .getBody();

            email = claims.getSubject();                    

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return null;
    }

}
