package com.example.springacturatoradmin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class DefaultSecurityConfig {

    @Bean
    public SecurityWebFilterChain defaultSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        return http.authorizeExchange(req -> req.pathMatchers("/assets/**")
                        .permitAll()
                        .pathMatchers("/login")
                        .permitAll()
                        .anyExchange()
                        .authenticated()).formLogin(withDefaults())
                .httpBasic(withDefaults())
                .csrf(ServerHttpSecurity.CsrfSpec::disable).build();
    }
}
