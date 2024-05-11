package com.example.testingspring.DataClass.ObjectForTaco;


import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "taco_order")
//  Отже, хоча обов'язковість реалізації Serializable для класів, вказаних в @SessionAttributes,
//  може залежати від конкретного середовища та його конфігурації, рекомендується робити такі класи
//  Serializable. Це може забезпечити більшу гнучкість і забезпечити коректну роботу в різних умовах використання.
public class TacoOrder implements Serializable {

    @Id
    private int id;

    @NotBlank(message = "not valid delivery name")
    @Column(name = "delivery_name")
    private String deliveryName;
    @Column(name = "delivery_street")
    private String deliveryStreet;
    @Column(name = "delivery_city")
    private String deliveryCity;
    @Column(name = "delivery_state")
    private String deliveryState;
    @Column(name = "delivery_zip")
    private String deliveryZip;
    @Temporal(TemporalType.DATE)
    private Date date;

    @CreditCardNumber(message = "not valid card number")
    @Column(name = "cc_number")
    private String ccNumber;

    @Pattern(regexp = "^((0[1-9])|(1[0-2]))([\\\\/])([0-9][0-9])$",message = "not valid expiration card")
    @Column(name = "cc_expiration")
    private String ccExpiration;

    @Digits(integer = 3,fraction = 0,message = "ccCVV isnt correct")
    @Column(name = "cc_cvv")
    private String ccCVV;

    @OneToMany(mappedBy = "tacoOrder",cascade = CascadeType.PERSIST)
    @Size(min = 1,message = "Need buy bigger then one taco")
    private List<Taco> tacoList = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addToTacoList(Taco taco){
        tacoList.add(taco);
    }


    @Override
    public String toString() {
        return "TacoOrder{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
