package org.example.playerone.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {
    private final String secretKey="jw7nxtoceJxiBI8RlHbFJya+ZZ8SO7qLEDixVwVuVEw=";
    private final SecretKey key=Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

    public String generateToken(String username){
        long exptime = 86400000L;
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ exptime))
                .signWith(key)
                .compact();
    }
    public String getUsername(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
