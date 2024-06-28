package com.example.testingspringwebflux.controller;


import com.example.testingspringwebflux.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
public class FirstReactiveController {

    // Це реалізація за допомогою анотацій, но краще при використанні spring webflux перейти на функціональну
    // парадигму. Приклад схожого контроллера таким методом (RouterFunctonConfig)

    @GetMapping("/")
    public Flux<String> stringMono(){
        return Flux.just("Hello"," First Test"," New Test").delayElements(Duration.ofSeconds(2)).doOnNext(s -> log.info(s)); // На даний момент я знаю 1 особливість Mono and Flux в Spring
        // WebFlux, а іменно те що потік не блокується (якщо правильно розумію, то дане завдання делегується іншому потоку з пула виконавчих потоків),
        // а значить ми зразу отримуємо обєкт без даних всередині, при звернені до Бд також не буде блокування потоку,
        // а само звернення буде відкладено, скоріше за все до якого часу першого використанні даних у методі (знання на момент 21.06.2024)
    }

}
