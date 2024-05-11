package com.example.testingspring.APIControllers;


import com.example.testingspring.DataClass.ObjectForTaco.Ingredients;
import com.example.testingspring.DataClass.ObjectForTaco.Taco;
import com.example.testingspring.DataClass.ObjectForTaco.TacoOrder;
import com.example.testingspring.DataClass.ObjectForTaco.User;
import com.example.testingspring.repository.wothSpringData.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/tacos",produces = MediaType.APPLICATION_JSON_VALUE) // Accept заголовок вказує на типи медіа-контенту, які клієнт готовий приймати від сервера
@CrossOrigin(origins = "http://localhost:8080")
@EnableMethodSecurity
public class ApiTacoController {
    private static TacoRepository tacoRepository;

    public ApiTacoController(TacoRepository tacoRepository){
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(params = "recent")
//    @PreAuthorize("hasRole('USER')")
    public Iterable<Taco> getRecentT(@RequestParam String recent) {
        Pageable pageable = PageRequest.of(0,10, Sort.by("createdAt").descending());
        System.out.println();
        return tacoRepository.findAll(pageable).getContent();
    }

    @PostMapping
    public String postRecentT() {
        return "Test";
    }


}
