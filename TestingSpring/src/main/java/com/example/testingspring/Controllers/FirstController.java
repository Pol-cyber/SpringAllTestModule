package com.example.testingspring.Controllers;


import com.example.testingspring.DataClass.ObjectForTaco.Ingredients;
import com.example.testingspring.DataClass.ObjectForTaco.Taco;
import com.example.testingspring.forConfiguration.ConfigurationForProfile;
import com.example.testingspring.forConfiguration.SecurityConfigurationClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;


@Controller
public class FirstController {


//    Тестовий код для профілів (див SecurityConfiguration)
//    public FirstController(@Nullable ConfigurationForProfile.TestBeanForProfile1 t1, @Nullable ConfigurationForProfile.TestBeanForProfile2 t2){
//        System.out.println(t1);
//        System.out.println(t2);
//    }


    @GetMapping("/")
    public String home(){
        return "home";
    }

}



