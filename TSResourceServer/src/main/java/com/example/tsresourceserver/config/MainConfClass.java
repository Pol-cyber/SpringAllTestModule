package com.example.tsresourceserver.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MainConfClass {



    @Bean
    public SecurityFilterChain configuration(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST,"/api/ingredients")
                    .hasAuthority("SCOPE_writeIngredients")
                    .requestMatchers(HttpMethod.DELETE,"/api/ingredients")
                    .hasAuthority("SCOPE_deleteIngredients").requestMatchers(HttpMethod.GET,"/api/ingredients").permitAll();
        }).oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer ->
                httpSecurityOAuth2ResourceServerConfigurer.jwt(jwtConfigurer ->
                        jwtConfigurer.jwkSetUri("http://localhost:9000/oauth2/jwks")));
        return httpSecurity.build();
    }

}
