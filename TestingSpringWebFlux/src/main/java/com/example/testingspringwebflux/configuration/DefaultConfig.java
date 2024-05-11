package com.example.testingspringwebflux.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultConfig {

    @Bean
    public RouterFunctionConfig.ReturnText returnText(){
        return new RouterFunctionConfig.ReturnText("Test1");
    }
}
