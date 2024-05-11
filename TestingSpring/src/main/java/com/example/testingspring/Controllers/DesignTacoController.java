package com.example.testingspring.Controllers;

import com.example.testingspring.DataClass.ObjectForTaco.Ingredients;
import com.example.testingspring.DataClass.ObjectForTaco.Taco;
import com.example.testingspring.DataClass.ObjectForTaco.TacoOrder;
import com.example.testingspring.DataClass.ObjectForTaco.User;
import com.example.testingspring.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

@Slf4j // дає можливість логування за допомогою  log.(любий method)
@Controller
@RequestMapping("/design") // дає основний шлях для даного контролера і якщо
// в @GetMapping etc. не вказано шлях то виконуватись метод буде в залежності від
// типу HHTP-запиту (Get,Post etc.)
@SessionAttributes("tacoOrder")
public class DesignTacoController {


    private IngredientRepository repository;

    @Autowired
    public DesignTacoController(IngredientRepository repository){
        this.repository = repository;
    }


    @ModelAttribute
    public void getDataTaco(Model model){
          model.addAttribute("wrap", repository.getAllIngredientThisType(Ingredients.Type.WRAP.name()));
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder tOrder(){
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco tTaco(){
        return new Taco();
    }

    @GetMapping
    public String getMethod(){
        return "design";
    }

    @PostMapping
    public String postMethod(Taco taco,@ModelAttribute TacoOrder tacoOrder){
        tacoOrder.setDate(new Date());
        tacoOrder.addToTacoList(taco);
        return "redirect:/orders/current";
    }

}
