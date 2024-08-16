package com.example.testingspring.restControllers;


import com.example.testingspring.DataClass.ObjectForTaco.Taco;
import com.example.testingspring.repository.wothSpringData.TacoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("Hello");
        return tacoRepository.findAll(pageable).getContent();
    }

    @PostMapping
    public String postRecentT() {
        return "Test";
    }


}
