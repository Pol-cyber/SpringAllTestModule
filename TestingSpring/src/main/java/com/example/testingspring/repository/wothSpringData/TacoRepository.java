package com.example.testingspring.repository.wothSpringData;

import com.example.testingspring.DataClass.ObjectForTaco.Ingredients;
import com.example.testingspring.DataClass.ObjectForTaco.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Arrays;
import java.util.Date;


// PagingAndSortingRepository створений для того щоб мати можливість передати pageable в стартовий метод
// () тобто не створювати його власноруч
@RepositoryRestResource(path = "tacos") // для того щоб налашутвати шлях автоматично, також можна використовувати
// анотацію @RestResource(path = "tacos") для самого класу сутності або методів всередині репозиторію
public interface TacoRepository extends PagingAndSortingRepository<Taco,Integer> , CrudRepository<Taco,Integer> {



}
