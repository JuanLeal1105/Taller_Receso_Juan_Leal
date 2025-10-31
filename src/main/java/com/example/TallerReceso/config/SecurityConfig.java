package com.example.TallerReceso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF para Swagger POST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",      // Ruta de Swagger UI
                                "/v3/api-docs/**",     // API Docs de Swagger
                                "/swagger-resources/**", 
                                "/webjars/**",
                                "/v3/api-docs.yaml",   // Docs adicionales
                                "/api/**"              // Tus endpoints REST
                        ).permitAll()  // Permite acceso sin autenticación
                        .anyRequest().authenticated() // Resto de la app requiere autenticación
                );

        return http.build();
    }
}

