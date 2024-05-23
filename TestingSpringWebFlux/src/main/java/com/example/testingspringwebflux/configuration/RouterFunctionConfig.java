package com.example.testingspringwebflux.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Configuration
public class RouterFunctionConfig {

    public static class ReturnText {

        private String text;

        public ReturnText(String text){
            this.text = text;
        }

        public String getText(){
            return text;
        }
    }

    ReturnText returnText;

    public RouterFunctionConfig(ReturnText check){
        this.returnText = check;
    }


    @Bean
    public RouterFunction<?> routerFunction(){
        return RouterFunctions.route(RequestPredicates.GET("/hello"),request -> ServerResponse.ok()
                .body(Mono.just(returnText.getText()), String.class));
    }
}
