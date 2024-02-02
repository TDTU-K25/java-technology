package com.example.lab09_10.controllers;

import com.example.lab09_10.models.MyResponseMessage;
import com.example.lab09_10.models.Product;
import com.example.lab09_10.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping("")
    @ResponseBody
    public ResponseEntity<MyResponseMessage<List<Product>>> getAll() {
        return productService.getAllProducts();
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<MyResponseMessage<String>> add(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MyResponseMessage<Product>> getById(@PathVariable Integer id) {
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MyResponseMessage<String>> update(@PathVariable Integer id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MyResponseMessage<String>> delete(@PathVariable Integer id) {
        return productService.delete(id);
    }
}
