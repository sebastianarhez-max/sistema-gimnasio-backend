package com.empresa.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration; // segundos

    private SecretKey key() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Authentication authentication) {
        logger.info(">>> generateToken");
        UsuarioPrincipal p = (UsuarioPrincipal) authentication.getPrincipal();

        Map<String, Object> claims = new HashMap<>();
        claims.put("idUsuario", p.getIdUsuario());
        claims.put("nombreCompleto", p.getNombreCompleto());
        claims.put("login", p.getLogin());
        claims.put("roles", p.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList()));

        Date now = new Date();
        return Jwts.builder()
                .claims(claims)
                .subject(p.getUsername())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expiration * 1000))
                .signWith(key())
                .compact();
    }

    public String getNombreUsuarioFromToken(String token) {
        return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key()).build().parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error(">>> validateToken >>> token expirado");
        } catch (JwtException e) {
            logger.error(">>> validateToken >>> token inválido: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error(">>> validateToken >>> token vacío");
        }
        return false;
    }
}
