package com.example.testingspring.springIntegration.fileSystem;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.*;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.dsl.*;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

@Configuration
public class ConfForIntegrFile {


    private int count = 0;



    @Bean
    public IntegrationFlow integrationFlow(){
        // Перевіряв як працює inboundAdapter - кожен новий файл буде оброблятись окремо
//        return IntegrationFlow.from(Files.inboundAdapter(new File("C:\\Users\\User\\IdeaProjects\\TestingSpring\\tmp\\sprintintegration\\files")),
//                        p -> p.poller(Pollers.fixedDelay(10000)))
//                .handle(message -> {
//                    System.out.println(message.getPayload());
//                }).get();
        // Перевіряв чи після сплітера всі дані будуть в різних потоках інтеграції - Так
//        return IntegrationFlow.from(MessageChannels.direct("textInChannel")).channel("splitChannel")
//                .channel(MessageChannels.direct("outSplitChannel"))
//                .handle(Files.outboundAdapter(new File("C:\\Users\\User\\IdeaProjects\\TestingSpring\\tmp\\sprintintegration\\files"))
//                        .fileExistsMode(FileExistsMode.APPEND)
//                        .appendNewLine(true))
//                .get();
        // також тут тестив роботу AbstractMessageRouter
      return IntegrationFlow.from("textInChannel")
              .<String,String>transform(text -> {text = text.toUpperCase();
                  try {
                      Thread.sleep(5000);
                  } catch (InterruptedException e) {
                      throw new RuntimeException(e);
                  }
                  count++;
                  System.out.println(count);
                  return text;
              }).channel("filtersssssChannel").channel("checRout").channel("lastchannel")
              .handle(Files
                      .outboundAdapter(new File("C:\\Users\\yarem\\IdeaProjects\\TestingSpring\\tmp\\sprintintegration\\files"))
                      .fileExistsMode(FileExistsMode.APPEND)
                      .appendNewLine(true))
              .get();
    }



    @Bean
    @Transformer(inputChannel="filtersssssChannel",outputChannel = "checRout")
    public GenericTransformer<String, String> romanNumTransformer() {
        return t -> {
            System.out.println("Check");
            return "nEW STRING";};
    }

    @Bean
    @Router(inputChannel = "checRout")
    public AbstractMessageRouter checkRouter(){
        return new AbstractMessageRouter() {
                    @Override
                    protected Collection<MessageChannel> determineTargetChannels(Message<?> message) {
                        LinkedList<MessageChannel> messageChannels = new LinkedList<>();
                        messageChannels.add(transformOne());
                        messageChannels.add(transformTwo());
                        return messageChannels;
                    }
                };
    }

    @Bean
    public MessageChannel transformOne(){
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "transformOne",outputChannel = "lastchannel")
    public GenericTransformer<String,String> transformerOne(){
        return new GenericTransformer<String, String>() {
            @Override
            public String transform(String source) {
                System.out.println(source);
                return "TransOne";
            }
        };
    }

    @Bean
    public MessageChannel transformTwo(){
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "transformTwo",outputChannel = "lastchannel")
    public GenericTransformer<String,String> transformerTwo(){
        return new GenericTransformer<String, String>() {
            @Override
            public String transform(String source) {
                System.out.println(source);
                return "TransTwo";
            }
        };
    }

//    @Bean
//    @Splitter(inputChannel = "splitChannel",outputChannel = "outSplitChannel")
//    public DataSplitter getSplitter(){
//        return new DataSplitter();
//    }

//    Налаштовує для DLS вище пуллер який буде перевіряти чи є повідомлення кожні 10 секунд і якщо є буде по черзі їх обробляти
//    @Bean(name = PollerMetadata.DEFAULT_POLLER)
//    public PollerSpec poller() {
//        return Pollers.fixedRate(10000)
//                .errorChannel("myErrors");
//    }

}


class DataSplitter {


    public Collection<Object> splitOrderIntoParts(String po) {
        ArrayList<Object> parts = new ArrayList<>();
        parts.addAll(Arrays.asList(po.split(" ")));
        return parts;
    }
// Буде помилка для @Splitter
//    public Collection<Object> splitOrderIntoPart(String po) {
//        ArrayList<Object> parts = new ArrayList<>();
//        parts.add("My");
//        parts.add("Collection");
//        return parts;
//    }
}
