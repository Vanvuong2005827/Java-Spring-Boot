package org.example.btvnbuoi9.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.btvnbuoi9.domain.entities.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.Signature;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    String secretKey;

    @Value("${jwt.expirationMs}")
    long validityInMs;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Authentication auth) {
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
        String username = auth.getName();
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMs);

        System.out.println(auth.getPrincipal());
        List<String> roles = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                .setSubject(String.valueOf(customUserDetails.getId()))
                .claim("username", username)
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, getSigningKey())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody();

        String username = claims.get("username", String.class);
        System.out.println(claims);
        @SuppressWarnings("unchecked")
        List<String> roles = (List<String>) claims.get("roles");


        var authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        UserDetails userDetails = new User(username, "", authorities);

        return new UsernamePasswordAuthenticationToken(
                userDetails, token, userDetails.getAuthorities()
        );
    }

    public void validateToken(String token) {
        Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token);

    }

}
