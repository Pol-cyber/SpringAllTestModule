package com.example.testingspringwebflux.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
public class FirstReactiveController {

    // Це реалізація за допомогою анотацій, но краще при використанні spring webflux перейти на функціональну
    // парадигму. Приклад схожого контроллера таким методом (RouterFunctonConfig)
    @GetMapping("/")
    public Flux<String> stringMono(){
        return Flux.just("Hello","First Test"); // На даний момент я знаю 1 особливість Mono and Flux в Spring
        // WebFlux, а іменно те що потік не блокується, а значить ми зразу отримуємо обєкт без даних всередині, при звернені
        // до Бд також не буде блокування потоку а само звернення буде відкладено, но до якого часу не знаю (на момент 04.05.2024)
    }

}
