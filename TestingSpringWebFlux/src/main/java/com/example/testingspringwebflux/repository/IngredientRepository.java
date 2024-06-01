package com.example.testingspringwebflux.repository;

import com.example.testingspringwebflux.domainObj.Ingredient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IngredientRepository extends ReactiveCrudRepository<Ingredient,Integer> {

}
