package com.example.springcommerce.services.impl;

import com.example.springcommerce.DTO.MyResponseMessage;
import com.example.springcommerce.models.Brand;
import com.example.springcommerce.models.Category;
import com.example.springcommerce.models.Color;
import com.example.springcommerce.models.Product;
import com.example.springcommerce.repository.BrandRepository;
import com.example.springcommerce.repository.CategoryRepository;
import com.example.springcommerce.repository.ColorRepository;
import com.example.springcommerce.repository.ProductRepository;
import com.example.springcommerce.services.ProductService;
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
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<Product> filter(String brandName, String categoryName, Double minPrice, Double maxPrice, String colorName) {
        return productRepository.filter(brandName, categoryName, minPrice, maxPrice, colorName);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProductsByBrand(Brand brand) {
        return productRepository.findAllByBrand(brand);
    }

    // API
    @Override
    public ResponseEntity<MyResponseMessage<List<Product>>> getAll() {
        Optional<List<Product>> products = Optional.of(productRepository.findAll());
        MyResponseMessage<List<Product>> responseMessage = new MyResponseMessage<>(Response.SC_OK, "Get all products successfully", products);
        return ResponseEntity.ok(responseMessage);
    }

    @Override
    public ResponseEntity<MyResponseMessage<String>> addProduct(Product product) {
        Category categoryFound = categoryRepository.findById(product.getCategory().getId()).get();
        Brand brandFound = brandRepository.findById(product.getBrand().getId()).get();
        Color colorFound = colorRepository.findById(product.getColor().getId()).get();
        product.setBrand(brandFound);
        product.setCategory(categoryFound);
        product.setColor(colorFound);
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
        Category categoryFound = categoryRepository.findById(newProduct.getCategory().getId()).get();
        Brand brandFound = brandRepository.findById(newProduct.getBrand().getId()).get();
        Color colorFound = colorRepository.findById(newProduct.getColor().getId()).get();
        Product productFound = productRepository.findById(id).orElse(null);
        String msg = "";
        if (productFound != null) {
            msg = "Update product successfully";
            BeanUtils.copyProperties(newProduct, productFound, "id");
            productFound.setBrand(brandFound);
            productFound.setCategory(categoryFound);
            productFound.setColor(colorFound);
            productRepository.save(productFound);
        } else {
            msg = "Product does not exist";
        }
        MyResponseMessage<String> responseMessage = new MyResponseMessage<>(Response.SC_OK, msg);
        return ResponseEntity.ok(responseMessage);
    }

    @Override
    public ResponseEntity<MyResponseMessage<String>> delete(Integer id) {
        productRepository.deleteByIdOfMy(id);
        MyResponseMessage<String> responseMessage = new MyResponseMessage<>(Response.SC_OK, "Delete product successfully");
        return ResponseEntity.ok(responseMessage);
    }
}
