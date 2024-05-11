package com.example.testingspring.DataClass;

import com.example.testingspring.DataClass.ObjectForTaco.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;


@Data
public class RegisterForm {

    private  String username;
    private  String password;
    private  String fullname;
    private  String street;
    private  String city;
    private  String state;
    private  String zip;
    private  String phoneNumber;

    public User getUser(PasswordEncoder passwordEncoder){
        return new User(username,passwordEncoder.encode(password),fullname,
                street,city,state,zip,phoneNumber);
    }
}
