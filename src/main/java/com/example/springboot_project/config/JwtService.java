package com.example.springboot_project.config;

import com.example.springboot_project.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//Сервис для работы с Jwt
@Service
public class JwtService {
    @Value("${token.signing.key}")
    private String jwtSecret;

    //Извлечение всех данных из токена
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //Извлечение определенных данных из токена данных из токена
    private <T> T extractClaims(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    //Извлечение email из токена
    public String extractEmail(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    //получение ключа для подписи токена
    private SecretKey getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(bytes);
    }

    //Генерация токена с данными пользователя
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof User customUser) {
            claims.put("id", customUser.getId());
            claims.put("role", customUser.getRole());
        }
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey())
                .compact();
    }

    //Проверка токена на валидность
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userEmail = extractEmail(token);
        return userEmail.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    //Проверка токена на просроченность
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //Извлечение даты истечения
    //6
    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
}
