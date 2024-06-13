package com.example.testingspringwebflux.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public SecurityWebFilterChain securityPathconfig(ServerHttpSecurity http){
         return http
                 .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
//                         .pathMatchers("/hello")
//                         .hasAuthority("USER")
                         .anyExchange().permitAll())
                 .build();
    }

}
