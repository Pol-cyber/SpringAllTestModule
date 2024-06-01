package com.example.testingspringwebflux.testReactiveRepo;


import com.example.testingspringwebflux.domainObj.Ingredient;
import com.example.testingspringwebflux.repository.IngredientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@DataR2dbcTest
public class TestIntgredientRepo {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    public void testIngredientRepo(){
        Ingredient ingredient = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);

        StepVerifier.create(ingredientRepository.save(ingredient))
                .assertNext(System.out::println).verifyComplete();
    }
}
