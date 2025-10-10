package com.dab.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsGlobalConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // dominios desde los que se puede acceder (ajusta a los tuyos)
        configuration.setAllowedOrigins(List.of(
                "https://book-social-proyect.vercel.app",  // Frontend en producción
                "http://localhost:4200"                   // Frontend local Angular
        ));

        // métodos HTTP permitidos
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // cabeceras permitidas
        configuration.setAllowedHeaders(List.of("*"));
        configuration.addAllowedHeader("Authorization");

        // cabeceras que se expondrán al navegador
        configuration.setExposedHeaders(List.of("Access-Control-Allow-Origin", "Access-Control-Allow-Methods"));

        // permitir credenciales (cookies o headers de autorización)
        configuration.setAllowCredentials(true);

        // aplicar a toda la API
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}