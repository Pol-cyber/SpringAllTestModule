package com.example.tsauthorizeserver.configurationpack;


import com.example.tsauthorizeserver.myObject.User;
import com.example.tsauthorizeserver.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class DefaultClassConf {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> userRepository.findByUsername(username);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
          return httpSecurity.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
              authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
                  })
                  .formLogin(withDefaults()).build();
    }

    @Bean
    public ApplicationRunner applicationRunner(UserRepository userRepository,PasswordEncoder encoder){
        return args -> {
          userRepository.save(new User("habuma", encoder.encode("password"), "ROLE_ADMIN"));
        };
    }

}
