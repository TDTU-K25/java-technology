package com.example.springcommerce.services;

import com.example.springcommerce.models.Order;

import java.util.List;

public interface OrderService {
    void createOrder();

    // API
    List<Order> getAllOrders();

    void addOrder(Order order);

    Order getById(Integer id);

    void update(Integer id, Order newOrder);

    void delete(Integer id);
}
