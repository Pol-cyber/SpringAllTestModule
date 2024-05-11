package com.example.testingspring.APIControllers.MessageArtemis;


import com.example.testingspring.DataClass.ObjectForTaco.Taco;
import com.example.testingspring.DataClass.ObjectForTaco.TacoOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
