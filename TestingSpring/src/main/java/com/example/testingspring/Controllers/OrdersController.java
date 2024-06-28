package com.example.testingspring.Controllers;

import com.example.testingspring.component.propertiesHolder.OrderPrpHolder;
import com.example.testingspring.DataClass.ObjectForTaco.User;
import com.example.testingspring.repository.wothSpringData.OrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.example.testingspring.DataClass.ObjectForTaco.TacoOrder;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrdersController {

    private OrderRepository orderRepository;
    private OrderPrpHolder prpHolder;

    @Autowired
    public OrdersController(OrderRepository orderRepository,OrderPrpHolder prpHolder){
        this.orderRepository = orderRepository;
        this.prpHolder = prpHolder;
    }


    @ModelAttribute("tacoOrder")
    public TacoOrder setTACO(Model model){
        return new TacoOrder();
    }


    @GetMapping("/current")
    public String currentOrder(@AuthenticationPrincipal User user, Model model){
        Pageable pageable = PageRequest.of(0,prpHolder.getPageSize());
        model.addAttribute("userorders", orderRepository.findByUserOrderByDateDesc(user,pageable));
        return "orderForm";
    }


    @PostMapping()
    public String confirmDeliver(@Valid TacoOrder tacoOrder,Errors errors,
            @AuthenticationPrincipal User user, SessionStatus sessionStatus){
        log.info(tacoOrder.toString());
        sessionStatus.setComplete();

        if(errors.hasErrors()) {
            return "orderForm";
        }
        tacoOrder.setUser(user);
        tacoOrder.setDate(new Date());

        orderRepository.save(tacoOrder);
        return "redirect:/";
    }
}
