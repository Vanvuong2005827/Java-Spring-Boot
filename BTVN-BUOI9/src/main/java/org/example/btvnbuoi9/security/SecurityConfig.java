package org.example.btvnbuoi9.security;

import jakarta.servlet.Filter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.btvnbuoi9.constants.AuthMessage;
import org.example.btvnbuoi9.constants.SecurityPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig {
    JwtAuthenticationFilter jwtAuthenticationFilter;
    CustomUserDetailsService uds;

    @Bean
    public DaoAuthenticationProvider daoAuthProvider() {
        var daoAuthProvider = new DaoAuthenticationProvider();
        daoAuthProvider.setUserDetailsService(uds);
        daoAuthProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthProvider;
    }

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CustomUserDetailsService uds) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.uds = uds;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, RestAuthenticationEntryPoint restAuthenticationEntryPoint) throws Exception {
        http
                .headers(
                        headers -> headers
                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::deny)
                                .contentSecurityPolicy(contentSecurityPolicyConfig ->
                                        contentSecurityPolicyConfig.policyDirectives("form-action 'self'")
                                )
                )
                .authenticationProvider(daoAuthProvider())
                .csrf(
                        csrf -> csrf.disable()
                )
                .cors(
                        cors -> cors.disable()
                )
                .exceptionHandling(
                        ex -> ex.authenticationEntryPoint(restAuthenticationEntryPoint)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, SecurityPath.Anonymous).anonymous()
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/login", "/swagger-ui/index.html").permitAll()
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring().requestMatchers(
//                "/swagger-ui/**", "/v3/api-docs/**"
//        );
//    }
}
