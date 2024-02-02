package com.example.lab09_10.services.impl;

import com.example.lab09_10.models.MyResponseMessage;
import com.example.lab09_10.models.Product;
import com.example.lab09_10.repository.ProductRepository;
import com.example.lab09_10.services.ProductService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseEntity<MyResponseMessage<List<Product>>> getAllProducts() {
        Optional<List<Product>> products = Optional.of(productRepository.findAll());
        MyResponseMessage<List<Product>> responseMessage = new MyResponseMessage<>(Response.SC_OK, "", products);
        return ResponseEntity.ok(responseMessage);
    }

    @Override
    public ResponseEntity<MyResponseMessage<String>> addProduct(Product product) {
        productRepository.save(product);
        MyResponseMessage<String> responseMessage = new MyResponseMessage<>(Response.SC_OK, "Add product successfully");
        return ResponseEntity.ok(responseMessage);
    }

    @Override
    public ResponseEntity<MyResponseMessage<Product>> getById(Integer id) {
        Optional<Product> productFound = productRepository.findById(id);
        MyResponseMessage<Product> responseMessage = new MyResponseMessage<>(Response.SC_OK, "Get product successfully", productFound);
        return ResponseEntity.ok(responseMessage);
    }

    @Override
    public ResponseEntity<MyResponseMessage<String>> update(Integer id, Product newProduct) {
        Product productFound = productRepository.findById(id).orElse(null);
        String msg = "";
        if (productFound != null) {
            msg = "Update product successfully";
            BeanUtils.copyProperties(newProduct, productFound, "code");
            productRepository.save(productFound);
        } else {
            msg = "Product does not exist";
        }
        MyResponseMessage<String> responseMessage = new MyResponseMessage<>(Response.SC_OK, msg);
        return ResponseEntity.ok(responseMessage);
    }

    @Override
    public ResponseEntity<MyResponseMessage<String>> delete(Integer id) {
        productRepository.deleteById(id);
        MyResponseMessage<String> responseMessage = new MyResponseMessage<>(Response.SC_OK, "Delete product successfully");
        return ResponseEntity.ok(responseMessage);
    }
}
