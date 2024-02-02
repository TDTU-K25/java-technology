package com.example.springcommerce.api;

import com.example.springcommerce.DTO.MyResponseMessage;
import com.example.springcommerce.models.Product;
import com.example.springcommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductControllerAPI {
    @Autowired
    ProductService productService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<MyResponseMessage<List<Product>>> getAll() {
        return productService.getAll();
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