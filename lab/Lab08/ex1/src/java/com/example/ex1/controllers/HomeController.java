package com.example.ex1.controllers;

import com.example.ex1.models.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("contact")
    public String showContactForm(Model model) {
        model.addAttribute("user", new User());
        return "contact";
    }

    @PostMapping("contact")
    public String showContactInformation(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        return "info";
    }

    @GetMapping(value = "about", produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String about() {
        return "About this site";
    }
}
