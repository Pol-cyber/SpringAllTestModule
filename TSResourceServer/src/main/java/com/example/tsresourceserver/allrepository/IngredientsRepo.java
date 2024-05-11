package com.example.tsresourceserver.allrepository;

import com.example.tsresourceserver.entityObj.Ingredients;
import org.springframework.data.repository.CrudRepository;

public interface IngredientsRepo extends CrudRepository<Ingredients,String> {
}
