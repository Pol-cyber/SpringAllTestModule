package com.example.testingspringwebflux.testControllers;

import com.example.testingspringwebflux.configuration.RouterFunctionConfig;
import com.example.testingspringwebflux.controller.FirstReactiveController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

public class TestFirstController {



    @Test
    public void testController(){
        Flux<String> f = Flux.just(new String[]{"Hello","First Test"});

        FirstReactiveController firstReactiveController = Mockito.mock(FirstReactiveController.class);
        Mockito.when(firstReactiveController.stringMono()).thenReturn(f);

        WebTestClient webTestClient = WebTestClient.bindToController(firstReactiveController).build();

        webTestClient.get().uri("/")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$").isEqualTo("HelloFirst Test");
        // також замість jsonPath можна використовувати json для передачі строки, що представляє
        // json (ст. 349)
        // Також  є можливість зрівнювати з масивом - expectBodyList (ст. 350)



    }

    @Test
    public void testFuncController(){
        Flux<String> f = Flux.just(new String[]{"Hello","First Test"});

        RouterFunctionConfig.ReturnText firstReactiveController = Mockito.mock(RouterFunctionConfig.ReturnText.class);
        Mockito.when(firstReactiveController.getText()).thenReturn("My Text");

        WebTestClient webTestClient = WebTestClient.bindToRouterFunction(new RouterFunctionConfig(firstReactiveController).routerFunction()).build();

        webTestClient.get().uri("/hello")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$").isEqualTo("My Text");
        // також замість jsonPath можна використовувати json для передачі строки, що представляє
        // json (ст. 349)
        // Також  є можливість зрівнювати з масивом - expectBodyList (ст. 350)



    }
}
