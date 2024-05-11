package com.example.testingspring.DataClass.ObjectForTaco;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
public class Taco {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;

    @Size(min = 1,message = "Count ingredients so low")
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "ingredient_ref")
    private List<Ingredients> ingredientsList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "taco_order")
    private TacoOrder tacoOrder;
}
