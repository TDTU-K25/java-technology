package com.example.lab09_10.services;

import com.example.lab09_10.models.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    void addOrder(Order order);

    Order getById(Integer id);

    void update(Integer id, Order newOrder);

    void delete(Integer id);
}
