package com.example.testingspring.Controllers.SimpleController;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class ConfigurationForSimpleController implements WebMvcConfigurer {

//    Закоментовано тому що вже є створений для цього шляху контролер FirstController
//    ( це лише можливий варіант оголошення простого контролера, тобто такого, що не
//    має внесення в модель чи форм і тд.
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("home");
    }
}
