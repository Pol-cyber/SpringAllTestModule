package com.example.testingspring.MyConverter;


import com.example.testingspring.DataClass.ObjectForTaco.Ingredients;
import com.example.testingspring.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class IngredientByIdConverter implements Converter<String, Ingredients> {

    private Map<String,Ingredients> map = new HashMap<>();
    private IngredientRepository repository;


    @Autowired
    public IngredientByIdConverter(IngredientRepository repository){
        this.repository = repository;
    }


    @Override
    public Ingredients convert(String source) {
        return repository.getIngredientsById(source);
    }
}
