package com.example.testingspring.restControllers.MessageArtemis;


import com.example.testingspring.DataClass.ObjectForTaco.TacoOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/message")
public class ControllerArtemis {

    private JmsTemplate jmsTemplate;

    @Autowired
    public ControllerArtemis(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("getmap")
    public void getRecentT() {
        TacoOrder tacoOrder = new TacoOrder();
        tacoOrder.setDate(new Date());
         jmsTemplate.convertAndSend("tacocloud.order.queue",tacoOrder);
    }
}
