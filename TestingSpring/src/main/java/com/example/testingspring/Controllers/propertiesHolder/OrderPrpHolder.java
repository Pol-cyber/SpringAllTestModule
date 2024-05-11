package com.example.testingspring.Controllers.propertiesHolder;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


@Data
@Component
@Validated
@ConfigurationProperties(prefix = "taco.orders")
public class OrderPrpHolder {

    @Min(value = 5, message = "Page min need be more 5 ")
    @Max(value = 25, message = "Page max need be less 25")
    private int pageSize = 19;

}
