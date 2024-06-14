package com.example.testingspringwebflux.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class RSocketController {

    @MessageMapping("/")
    public Mono<String> stringMono(Mono<String> stringMono){
        return stringMono.doOnNext(s -> {
            log.info("Message Receive");
        }).map(mapper -> "New String return");
    }
}
