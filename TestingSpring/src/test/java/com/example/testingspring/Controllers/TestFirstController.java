package com.example.testingspring.Controllers;


import com.example.testingspring.Controllers.FirstController;
import com.example.testingspring.Controllers.propertiesHolder.OrderPrpHolder;
import com.example.testingspring.repository.IngredientRepository;
import com.example.testingspring.repository.wothSpringData.OrderRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(FirstController.class)
public class TestFirstController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngredientRepository ingredientRepository;

    @Test
    public void testHomePage() throws Exception {
//          mockMvc.perform(MockMvcRequestBuilders.get("/"))
//                  .andExpect(MockMvcResultMatchers.status().isOk())
//                  .andExpect(MockMvcResultMatchers.view().name("home"))
//                  .andExpect(MockMvcResultMatchers.content().string(Matchers
//                          .containsString("Hello World")));
    }
}
