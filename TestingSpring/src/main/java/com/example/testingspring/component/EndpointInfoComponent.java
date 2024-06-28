package com.example.testingspring.component;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class EndpointInfoComponent implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String,String> stringStringMap = new HashMap<>();
        stringStringMap.put("Book","Spring in Action 6th Edition");
        builder.withDetail("Info for Application",stringStringMap);
    }
}
