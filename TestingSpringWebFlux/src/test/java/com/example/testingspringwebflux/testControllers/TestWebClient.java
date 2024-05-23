package com.example.testingspringwebflux.testControllers;


import io.netty.handler.codec.http.HttpStatusClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestWebClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private WebClient webClient;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        this.webClient = webClientBuilder.baseUrl("http://localhost:" + port).build();
    }

    @Test
    public void testOnStatus() throws InterruptedException {
        Mono<String> mono = webClient.get().uri("/hello")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Server error")))
                .bodyToMono(String.class);

        mono.subscribe(System.out::println,s -> System.out.println("Client or Server Error - "+s.getMessage()));

        Thread.sleep(200);
//        StepVerifier.create(mono)
//                .expectNext("Hello, World!") // Ожидаемый ответ от сервера
//                .verifyComplete();
    }
}
