package com.backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    @Value("${token.secret.key}")
    private String secret;

    @Value("${token.secret.issuer}")
    private String issuer;
    // 사용자 이름
    private Date now = new Date();  // 현재 시간
    private Date validity = new Date(now.getTime() + 3600000);  // 만료 시간 (1시간 후)

    public String createToken(String username, String role) {
        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validity)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        // "role" claim에서 역할 정보 추출
        return claims.get("role", String.class);
    }

    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
