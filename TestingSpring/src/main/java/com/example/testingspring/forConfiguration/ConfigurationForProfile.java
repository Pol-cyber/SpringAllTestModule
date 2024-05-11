package com.example.testingspring.forConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile({"!test1","test2"}) // для всієї конфігурації
public class ConfigurationForProfile {
    //    Тестові біни для окремих профілів
    public static class TestBeanForProfile1 {

    }
    public static class TestBeanForProfile2 {

    }


    @Bean
//    @Profile("test1")
    public TestBeanForProfile1 getTest1(){
        return new TestBeanForProfile1();
    }

    @Bean
//    @Profile("test2")
    public TestBeanForProfile2 getTest2(){
        return new TestBeanForProfile2();
    }
}
