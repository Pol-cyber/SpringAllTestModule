package com.example.testingspringwebflux.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
@Slf4j
public class RSocketController {

    @MessageMapping("/requestResponse")
    public Mono<String> rResponse(Mono<String> stringMono){
        return stringMono.doOnNext(s -> {
            log.info("Message Receive");
        }).map(mapper -> "New String return");
    }


    @MessageMapping("/requestStream")
    public Flux<Character> rStream(Mono<String> stringMono){
        return stringMono.flux().flatMap(str -> Flux.fromArray(toCharacterArray(str)))
                .delayElements(Duration.ofSeconds(1));
    }

    @MessageMapping("/fire-and-forget")
    public Mono<Void> fForget(Mono<String> s){
        return s.doOnNext(s1 -> log.info(s1)).thenEmpty(Mono.empty());
    }

    @MessageMapping("/channel")
    public Flux<String> fForget(Flux<String> s){
        return s.doOnNext(s1 -> log.info(s1 +" Server"));
    }

    private Character[] toCharacterArray(String str) {
        char[] charArray = str.toCharArray();
        Character[] characterArray = new Character[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            characterArray[i] = charArray[i];
        }
        return characterArray;
    }
}
