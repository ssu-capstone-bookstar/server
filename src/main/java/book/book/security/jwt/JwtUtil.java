package book.book.security.jwt;


import book.book.security.auto.dto.AuthDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtProperties jwtProperties;


    public String generateAccessToken(AuthDto authDto) {
        Claims claims = createClaims(authDto);
        Date now = new Date();
        return createToken(claims, new Date(now.getTime() + jwtProperties.getAccessTokenExpiration()));
    }

    public String generateRefreshToken(AuthDto authDto) {
        Claims claims = createClaims(authDto);
        Date now = new Date();
        return createToken(claims, new Date(now.getTime() + jwtProperties.getRefreshTokenExpiration()));
    }

    public Claims createClaims(AuthDto authDto){
        Claims claims = Jwts.claims();
        claims.setSubject(authDto.getProviderId());
        claims.put("providerId", authDto.getProviderId());
        claims.put("id", authDto.getId());
        claims.put("email", authDto.getEmail());
        claims.put("role", authDto.getRole());
        return claims;
    }

    public String getProviderId(String token){
        return getClaims(token, getSignInKey()).toString();
    }

    public Date parseExpiration(String token) {
        return getClaims(token, getSignInKey()).getBody().getExpiration();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }


    private Jws<Claims> getClaims(String token, Key key) {
        return Jwts.parserBuilder()
                .requireIssuer(jwtProperties.getIssuer())
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }


    public String createToken(Claims claims, Date expiry){
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }


}
