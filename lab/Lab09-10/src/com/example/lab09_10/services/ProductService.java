package com.example.lab09_10.services;

import com.example.lab09_10.models.MyResponseMessage;
import com.example.lab09_10.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<MyResponseMessage<List<Product>>> getAllProducts();

    ResponseEntity<MyResponseMessage<String>> addProduct(Product product);

    ResponseEntity<MyResponseMessage<Product>> getById(Integer id);

    ResponseEntity<MyResponseMessage<String>> update(Integer id, Product newProduct);

    ResponseEntity<MyResponseMessage<String>> delete(Integer id);
}
