package com.example.tsresourceserver.restApi;

import com.example.tsresourceserver.allrepository.IngredientsRepo;
import com.example.tsresourceserver.entityObj.Ingredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/ingredients", produces="application/json")
@CrossOrigin(origins="http:/ /localhost:8080")
//@EnableMethodSecurity // для того щоб анотація @PreAuthorize("#{hasRole('ADMIN')}") працювала но тут цього немає
public class IngredientRest {

    private IngredientsRepo repo;

    @Autowired
    public IngredientRest(IngredientsRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Ingredients> allIngredients() {
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredients saveIngredient(@RequestBody Ingredients ingredient) {
        return repo.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") String ingredientId) {
        repo.deleteById(ingredientId);
    }
}
