package com.example.testingspring.services;


import com.example.testingspring.DataClass.ObjectForTaco.TacoOrder;
import com.example.testingspring.DataClass.ObjectForTaco.User;
import com.example.testingspring.repository.IngredientRepository;
import com.example.testingspring.services.interfaceforserv.OrderMessagingService;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JMSOrderServiceMessage  implements OrderMessagingService {
    public JmsTemplate jmsTemplate;


    @Autowired
    public JMSOrderServiceMessage(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendOrder(TacoOrder order) {


        jmsTemplate.send(new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(order);
            }
        });

    }
}
