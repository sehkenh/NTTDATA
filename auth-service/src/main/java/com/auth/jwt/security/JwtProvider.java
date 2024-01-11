package com.auth.jwt.security;

import com.auth.jwt.entity.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class JwtProvider {

    /*@Value("${jwt.secret}")*/
    private String secret;

    @PostConstruct
    protected void init(){
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }
    public String createToken(AuthUser authUser){
        Claims claims;
        claims = Jwts.claims().setSubject(authUser.getUserName());
        claims.put("id", authUser.getId());
        Date now = new Date();
        Date exp = new Date(now.getTime() + 36000000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validate(String token) {
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJwt(token);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    public String getUserNameFromToken(String token){
        try{
            return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody().getSubject();
        }catch (Exception exception){
            return "Bad token";
        }
    }
}
