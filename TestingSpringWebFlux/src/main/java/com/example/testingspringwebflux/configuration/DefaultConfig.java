package com.example.testingspringwebflux.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DefaultConfig {

    @Bean
    public RouterFunctionConfig.ReturnText returnText(){
        return new RouterFunctionConfig.ReturnText("Test1");
    }

//    @Bean
//    public WebClient beanWebClient(){
//        return WebClient.create("http://localhost:8080");
//    }
}
