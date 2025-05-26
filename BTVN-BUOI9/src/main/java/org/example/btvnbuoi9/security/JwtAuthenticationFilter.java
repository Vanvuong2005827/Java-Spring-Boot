package org.example.btvnbuoi9.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.btvnbuoi9.constants.TokenError;
import org.example.btvnbuoi9.exception.extendedExceptions.JwtAuthenticationException;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    JwtTokenProvider tokenProvider;
    RestAuthenticationEntryPoint entryPoint;

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        String path = request.getServletPath();
//        return path.startsWith("/v3/api-docs")
//                || path.startsWith("/swagger-ui")
//                || path.equals("/swagger-ui.html");
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                tokenProvider.validateToken(token);
                Authentication auth = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (MalformedJwtException ex) {
                entryPoint.commence(request, response,
                        new JwtAuthenticationException(TokenError.Token.Malformed));
                return;
            } catch (io.jsonwebtoken.security.SignatureException ex) {
                entryPoint.commence(request, response,
                        new JwtAuthenticationException(TokenError.Token.Signature));
                return;
            } catch (ExpiredJwtException ex) {
                entryPoint.commence(request, response,
                        new JwtAuthenticationException(TokenError.Token.Expired));
                return;
            } catch (UnsupportedJwtException ex) {
                entryPoint.commence(request, response,
                        new JwtAuthenticationException(TokenError.Token.Unsupported));
                return;
            } catch (IllegalArgumentException ex) {
                entryPoint.commence(request, response,
                        new JwtAuthenticationException(TokenError.Token.Blank));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
