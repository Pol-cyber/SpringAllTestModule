package com.example.testingspring.springIntegration.fileSystem;


import com.example.testingspring.DataClass.ObjectForTaco.Taco;
import com.example.testingspring.DataClass.ObjectForTaco.TacoOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.concurrent.Future;

@RestController
@RequestMapping(path = "/spintegration/file")
public class RestForIntFile {

    private MessagingFileGateway messagingFileGateway;
    private ApplicationContext applicationContext;


    public RestForIntFile(MessagingFileGateway messagingFileGateway,ApplicationContext applicationContext){
        this.messagingFileGateway = messagingFileGateway;
        this.applicationContext = applicationContext;
    }

    @GetMapping
    public void dataToFile(@RequestParam String data) throws InterruptedException {
//        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
        messagingFileGateway.writeToFile(data,"SPintegrationTofile");
        System.out.println("Hello my name IS PABLO");
//        messagingFileGateway.writeToFile(data,"SPintegrationTofile2");
//        System.out.println("H");
////        Thread.sleep(8000);
//        System.out.println("Start");
//        messagingFileGateway.writeToFile(data,"SPintegrationTofile3");
//        System.out.println("End");
    }


}

