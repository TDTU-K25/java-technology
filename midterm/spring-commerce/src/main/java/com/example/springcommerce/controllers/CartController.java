package com.example.springcommerce.controllers;

import com.example.springcommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @GetMapping("")
    public String showCartPage() {
        return "user/cart";
    }

    @GetMapping("/add/{productId}")
    public RedirectView add(@PathVariable int productId) {
        cartService.add(productId);
        return new RedirectView("/");
    }

    @PostMapping(value = "/remove/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> remove(@PathVariable int productId) {
        cartService.remove(productId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Remove cart item successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/clear")
    public RedirectView clear() {
        cartService.clear();
        return new RedirectView("/");
    }

    @PostMapping(value = "/update/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> update(@PathVariable int productId, @RequestBody int quantity) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Update quantity successfully");
        cartService.update(productId, quantity);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
