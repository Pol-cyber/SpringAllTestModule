package com.example.testingspring.springIntegration.fileSystem;


import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingGateway(defaultRequestChannel = "textInChannel")
public interface MessagingFileGateway {

    void writeToFile(@Payload String data, @Header(FileHeaders.FILENAME) String filename);

}
