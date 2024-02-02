package com.example.lab09_10.controllers;

import com.example.lab09_10.models.MyResponseMessage;
import com.example.lab09_10.models.Order;
import com.example.lab09_10.models.Product;
import com.example.lab09_10.repository.OrderRepository;
import com.example.lab09_10.services.OrderService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    public OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("")
    public ResponseEntity<MyResponseMessage<List<Order>>> getAllOrders() {
        MyResponseMessage<List<Order>> responseMessage = new MyResponseMessage<>(Response.SC_OK, "", Optional.of(orderService.getAllOrders()));
        return ResponseEntity.ok(responseMessage);
    }

    @PostMapping("")
    public ResponseEntity<MyResponseMessage<String>> add(@RequestBody Order order) {
        orderService.addOrder(order);
        MyResponseMessage<String> responseMessage = new MyResponseMessage<>(Response.SC_OK, "Add order successfully");
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MyResponseMessage<Order>> getById(@PathVariable Integer id) {
        MyResponseMessage<Order> responseMessage = new MyResponseMessage<>(Response.SC_OK, "Get order successfully", Optional.of(orderService.getById(id)));
        return ResponseEntity.ok(responseMessage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MyResponseMessage<String>> update(@PathVariable Integer id, @RequestBody Order order) {
        Order orderFound = orderRepository.findById(id).orElse(null);
        String msg = "";
        if (orderFound != null) {
            msg = "Update product successfully";
            orderService.update(id, order);
        } else {
            msg = "Product does not exist";
        }
        MyResponseMessage<String> responseMessage = new MyResponseMessage<>(Response.SC_OK, msg);
        return ResponseEntity.ok(responseMessage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MyResponseMessage<String>> delete(@PathVariable Integer id) {
        orderService.delete(id);
        MyResponseMessage<String> responseMessage = new MyResponseMessage<>(Response.SC_OK, "Delete order successfully");
        return ResponseEntity.ok(responseMessage);
    }
}
