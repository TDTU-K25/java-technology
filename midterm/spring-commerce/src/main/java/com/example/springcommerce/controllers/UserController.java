package com.example.springcommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/loginForm";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "auth/registerForm";
    }
}
