package com.example.testreactivee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;

@SpringBootApplication
public class TestReactiveeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestReactiveeApplication.class, args);
    }

}
