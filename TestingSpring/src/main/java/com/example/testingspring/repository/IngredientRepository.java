package com.example.testingspring.repository;

import com.example.testingspring.DataClass.ObjectForTaco.Ingredients;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.validation.ObjectError;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class IngredientRepository {

    private JdbcTemplate jdbcTemplate;

//    Автоматично передається оскільки класс являється репозиторієм (можна явно вказати @Autowired
//    у випадку якщо більще одного конструктора)
    public IngredientRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Iterable<Ingredients> getAllIngredients(){
        List<Ingredients> list = jdbcTemplate.query("SELECT * FROM ingredients",this::mapperToIngredient);
        return list;
    }

    public Iterable<Ingredients> getAllIngredientThisType(String type){
        List<Ingredients> list = jdbcTemplate.query("SELECT * FROM ingredients WHERE type = ?",this::mapperToIngredient,type);
        return list;
    }


    public Ingredients getIngredientsById(String id){
//        в ідеалі потрібно перевірити чи цей обєкт існує, но оскільки в моїй системі ніхто не буде видляти
//        вже існуючі інгрідієнти, а їх список базується на БД, після чого вони привязуються до чекбоксу - значення
//        буде існувати
        return jdbcTemplate.query("SELECT * FROM ingredients WHERE id=?",this::mapperToIngredient,id).get(0);

    }

    public Ingredients mapperToIngredient(ResultSet resultSet, int rowCount) throws SQLException {
         return new Ingredients(resultSet.getString("id"),resultSet.getString("name"),
                 Ingredients.Type.valueOf(resultSet.getString("type")));
    }

}
