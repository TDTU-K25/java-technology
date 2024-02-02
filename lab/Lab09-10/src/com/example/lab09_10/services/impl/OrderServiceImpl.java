package com.example.lab09_10.services.impl;

import com.example.lab09_10.models.Order;
import com.example.lab09_10.repository.OrderRepository;
import com.example.lab09_10.services.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order getById(Integer id) {
        Optional<Order> orderFound = orderRepository.findById(id);
        return orderFound.orElse(null);
    }

    @Override
    public void update(Integer id, Order newOrder) {
        Order orderFound = orderRepository.findById(id).orElse(null);
        if (orderFound != null) {
            BeanUtils.copyProperties(newOrder, orderFound, "orderNumber");
            orderRepository.save(orderFound);
        }
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
}
