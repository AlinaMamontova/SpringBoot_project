package com.example.springboot_project.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
//работа с токеном
//класс нужен для валидации токена
@Service
public class JwtService {
    private static final String SECRET_KEY = "54960c24ef15b090a920b131d10cd54001572d02f0be1046724b57fc61894e16";
    //достать одно конкретное утверждение
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    //генерация токена без extraClaims, только из данных самого пользователя
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
    //в Map содержатся доп.утверждения, которые мы хотим добавить в Token
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey())
                .compact();
    }
    //валидация токена
    //userdetails нужен, чтобы проверить принадлежит ли токен пользователю
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractEmail(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    //isTokenExpired() - истек или нет токен
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    //извлечь дату истечения
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //достать одно утверждение
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    //извлекаем весь payload, все claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    //получение ключа подписи
    private SecretKey getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }
}
