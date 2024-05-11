package com.example.testingspring.Controllers;


import com.example.testingspring.DataClass.RegisterForm;
import com.example.testingspring.repository.wothSpringData.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping
    public String registerForm(){
        return "registerForm";
    }


    @PostMapping
    public String postRegisterForm(RegisterForm registerForm){
        userRepository.save(registerForm.getUser(passwordEncoder));
        return "redirect:/login";
    }
}
