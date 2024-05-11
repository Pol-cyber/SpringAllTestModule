package com.example.testingspring.DataClass.ObjectForTaco;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;


@Entity
@Data
public class Ingredients {

    @Id
    private String id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Type type;


    public Ingredients() {

    }

    public enum Type implements Serializable {
        WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
    }


    public Ingredients(String id, String name, Type type){
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
