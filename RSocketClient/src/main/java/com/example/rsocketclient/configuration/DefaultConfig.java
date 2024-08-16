package com.example.rsocketclient.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Configuration
public class DefaultConfig {

//    @Autowired
//    private WebClient.Builder webClientBuilder;

    @Bean
    public ApplicationRunner applicationRunner(RSocketRequester.Builder builder){
        return args -> {
             RSocketRequester requester = builder.tcp("localhost",7000); // 7000 це порт
            // який слує RSocket сервер в проекті TestingSpringWebFlux

             // Request-response
//             requester.route("/requestResponse")
//                     .data("Hello")
//                     .retrieveMono(String.class)
//                     .subscribe(s -> System.out.println("Result is "+s));

            // request-stream
//            requester.route("/requestStream")
//                     .data("Hello")
//                     .retrieveFlux(Character.class)
//                     .subscribe(s -> System.out.println("Character is  "+s));

            // fire-and-forget
//            requester.route("/fire-and-forget")
//                    .data("TestFire")
//                    .send() // method 'send' because we don't expect result
//                    .subscribe();

            // channel
            Flux<String> flux = Flux.just("Test","Channel","Model").delayElements(Duration.ofSeconds(1));
            requester.route("/channel")
                    .data(flux)
                    .retrieveFlux(String.class) // method 'send' because we don't expect result
                    .subscribe(s -> System.out.println(s +" Result"));


            // WebClient це аналог RestTemplate і тут я просто роблю запит до сервер TestingSpringWebFlux,
            // щоб перевірити (4) в fileForKnow розміщений в проекті згаданому вище.
//            WebClient webClient = webClientBuilder.baseUrl("http://localhost:" + 8080).build();
//            webClient.get().uri("/")
//                    .retrieve()
//                    .bodyToFlux(String.class)
//                    .subscribe(s -> System.out.println(s));
        };
    }
}
