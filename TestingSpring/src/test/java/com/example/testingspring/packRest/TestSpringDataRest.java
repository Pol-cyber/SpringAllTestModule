package com.example.testingspring.packRest;


import com.example.testingspring.DataClass.ObjectForTaco.Ingredients;
import com.example.testingspring.DataClass.ObjectForTaco.Taco;
import com.example.testingspring.DataClass.ObjectForTaco.TacoOrder;
import com.example.testingspring.DataClass.ObjectForTaco.User;
import com.example.testingspring.TestingSpringApplication;
import com.example.testingspring.repository.wothSpringData.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.Date;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSpringDataRest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

//    @Test
//    public void testGetAllTacos() {
//        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/data-api/tacos", String.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        // Перевірте, чи відповідь містить очікувані дані або структуру
//        // Можливо, вам захочеться використовувати JSON-парсер або бібліотеку, таку як AssertJ JSON.
//    }

    @Test
    public void testPost(){
        try {

//            ResponseEntity<Taco> r = restTemplate.getForEntity("http://localhost:8080/data-api/tacos/{id}", Taco.class,352);
            Taco taco = new Taco();
            taco.setCreatedAt(new Date());
            taco.setName("Test");
            Ingredients ingredients = new Ingredients();
            ingredients.setId("24");
            ingredients.setType(Ingredients.Type.CHEESE);
            ingredients.setName("Hello");
            taco.setIngredientsList(Arrays.asList(ingredients));
            URI newResourceLocation = restTemplate.postForLocation("http://localhost:"+port+"/data-api/tacos", taco);
            System.out.println(newResourceLocation);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Test failed with exception: " + e.getMessage());
        }
    }
}
