package com.example.springcommerce.services;

import com.example.springcommerce.DTO.MyResponseMessage;
import com.example.springcommerce.models.Brand;
import com.example.springcommerce.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<Product> filter(String brandName, String categoryName, Double minPrice, Double maxPrice, String colorName);

    List<Product> getAllProducts();

    Product getProductById(Integer id);

    List<Product> getAllProductsByBrand(Brand brand);

    // API
    ResponseEntity<MyResponseMessage<List<Product>>> getAll();

    ResponseEntity<MyResponseMessage<String>> addProduct(Product product);

    ResponseEntity<MyResponseMessage<Product>> getById(Integer id);

    ResponseEntity<MyResponseMessage<String>> update(Integer id, Product newProduct);

    ResponseEntity<MyResponseMessage<String>> delete(Integer id);
}
