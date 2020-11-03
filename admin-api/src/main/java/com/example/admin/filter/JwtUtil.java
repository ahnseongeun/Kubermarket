package com.example.admin.filter;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;

@Slf4j
public class JwtUtil {

    private Key key;

    public JwtUtil(String secret){
        log.info(secret);
        this.key= Keys.hmacShaKeyFor(secret.getBytes());
        log.info(String.valueOf(key));
    }
    //다중로그인처리는 어떻게?
    public String creatToken(String email,String nickName){
        JwtBuilder builder= Jwts.builder()
                .claim("email",email)
                .claim("nickName",nickName);
        return builder.signWith(SignatureAlgorithm.HS256, key).compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}
