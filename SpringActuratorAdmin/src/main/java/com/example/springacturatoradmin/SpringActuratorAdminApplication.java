package com.example.springacturatoradmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class SpringActuratorAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringActuratorAdminApplication.class, args);
    }

}
