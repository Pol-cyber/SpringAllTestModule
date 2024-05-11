package com.example.testingspring.services.interfaceforserv;

import com.example.testingspring.DataClass.ObjectForTaco.TacoOrder;

public interface OrderMessagingService {

    void sendOrder(TacoOrder order);
}
