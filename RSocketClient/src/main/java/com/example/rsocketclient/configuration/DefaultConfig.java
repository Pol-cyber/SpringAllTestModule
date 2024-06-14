package com.example.rsocketclient.configuration;


import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Configuration
public class DefaultConfig {

    @Bean
    public ApplicationRunner applicationRunner(RSocketRequester.Builder builder){
        return args -> {
             RSocketRequester requester = builder.tcp("localhost",7000);

             requester.route("/")
                     .data("Hello")
                     .retrieveMono(String.class)
                     .subscribe(s -> System.out.println("Result is "+s));
        };
    }
}
