package com.example.rsocketclient.configuration;


import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Configuration
public class DefaultConfig {

    @Bean
    public ApplicationRunner applicationRunner(RSocketRequester.Builder builder){
        return args -> {
             RSocketRequester requester = builder.tcp("localhost",7000);

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

        };
    }
}
