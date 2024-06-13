package com.example.testingspringwebflux.testControllers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestWebTestClientWithRealEnvironment {


    @Autowired
    WebTestClient webTestClient;

    @Test
    public void testController(){
         webTestClient.get().uri("/hello")
                 .accept(MediaType.APPLICATION_JSON)
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody().jsonPath("$").isEqualTo("Test1");
    }
}
