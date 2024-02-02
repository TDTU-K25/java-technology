package com.example.springcommerce.controllers;

import com.example.springcommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("")
    public RedirectView createOrder() {
        orderService.createOrder();
        return new RedirectView("/");
    }
}
