package com.example.springcommerce.controllers;

import com.example.springcommerce.services.CategoryService;
import com.example.springcommerce.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    HttpSession session;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProducts());
        return "user/index";
    }

    @GetMapping("/product-detail")
    public String shopDetails() {
        return "user/product-detail";
    }

    @GetMapping("/checkout")
    public String checkoutOrder() {
        return "user/checkout";
    }
}
