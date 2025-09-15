package com.filmfusion.securityconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
                // Allow GET requests for APIs & static resources
                .requestMatchers(HttpMethod.GET,
                    "/",
                    "/health",
                    "/bollywood/**",
                    "/tollywood/**",
                    "/kollywood/**",
                    "/actuator/health",
                    "/index.html",
                    "/css/**",
                    "/js/**",
                    "/images/**").permitAll()
                // Allow OPTIONS for CORS preflight
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // All other requests require authentication
                .anyRequest().authenticated()
            )
            .httpBasic(basic -> basic.disable()); // disable browser login popup

        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Explicitly allow dev & prod origins
        config.setAllowedOrigins(List.of(
            "http://localhost:3000",               // your local frontend
            "http://localhost:8080",               // local backend test
            "https://filmfusion-backend-bnwi.onrender.com" // deployed backend
        ));

        // Allow main HTTP methods
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Allow all headers
        config.setAllowedHeaders(List.of("*"));
        // Allow credentials if needed (cookies, Authorization header, etc.)
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
