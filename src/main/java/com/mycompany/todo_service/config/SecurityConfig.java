package com.mycompany.todo_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public CorsConfiguration corsConfiguration() {
    CorsConfiguration corsConfig = new CorsConfiguration();
    corsConfig.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080")); // Adjust as needed for
                                                                                             // production

    corsConfig.setAllowedHeaders(List.of("Authorization", "Content-Type")); // Allow specific headers
    corsConfig.setAllowCredentials(true); // Allow credentials if necessary
    return corsConfig;
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080"));
    config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
    config.setAllowCredentials(true);
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfiguration corsConfiguration)
      throws Exception {
    http
        .csrf(csrf -> csrf.disable()) // Disable CSRF for testing purposes
        .cors(cors -> cors.configurationSource(request -> corsConfiguration))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
            .requestMatchers("/api/**").permitAll() // Permit all requests to /api/**
            .anyRequest().authenticated());
    return http.build();
  }
}
