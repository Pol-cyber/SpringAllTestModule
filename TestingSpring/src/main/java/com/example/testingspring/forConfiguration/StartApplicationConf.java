package com.example.testingspring.forConfiguration;


import com.example.testingspring.DataClass.ObjectForTaco.Ingredients;
import com.example.testingspring.DataClass.ObjectForTaco.Taco;
import com.example.testingspring.repository.IngredientRepository;
import com.example.testingspring.repository.wothSpringData.TacoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Configuration
public class StartApplicationConf {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
//    Створює стартові дані на початку но після не видаляє їх!!!!!!!!!!!!
//    @Bean
//    public CommandLineRunner dataLoad(IngredientRepository ingredientRepository, TacoRepository tacoRepository){
//       return args -> {
//           Ingredients ingredients = ingredientRepository.getIngredientsById("FLTO");
//           Taco taco = new Taco();
//           taco.setCreatedAt(new Date());
//           taco.setIngredientsList(new ArrayList<>(Arrays.asList(ingredients)));
//           tacoRepository.save(taco);
//       };
//
//    }
}
