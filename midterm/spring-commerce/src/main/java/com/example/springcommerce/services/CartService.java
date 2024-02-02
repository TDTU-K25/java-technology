package com.example.springcommerce.services;

public interface CartService {
    void add(int productId);

    void clear();

    void update(int productId, int quantity);

    void remove(int productId);
}
