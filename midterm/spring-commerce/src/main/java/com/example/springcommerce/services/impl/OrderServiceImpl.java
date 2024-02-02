package com.example.springcommerce.services.impl;

import com.example.springcommerce.DTO.CartItem;
import com.example.springcommerce.enums.OrderStatus;
import com.example.springcommerce.models.Order;
import com.example.springcommerce.models.OrderDetail;
import com.example.springcommerce.models.OrderDetailKey;
import com.example.springcommerce.models.User;
import com.example.springcommerce.repository.OrderRepository;
import com.example.springcommerce.repository.ProductRepository;
import com.example.springcommerce.services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    HttpSession session;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void createOrder() {
        // create order
        Order order = new Order();
        User user = (User) session.getAttribute("user");
        Map<Integer, CartItem> cart = (HashMap<Integer, CartItem>) session.getAttribute("cart");
        Double totalSellingPrice = cart.values().stream().mapToDouble(CartItem::getTotalPrice).sum();

        order.setUser(user);
        order.setCreatedAt(new Date(System.currentTimeMillis()));
        order.setTotalSellingPrice(totalSellingPrice);
        order.setStatus(OrderStatus.CREATED);

        orderRepository.save(order);

        order = orderRepository.getLatestOrder();

        // add order detail
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (CartItem cartItem : cart.values()) {
            orderDetails.add(new OrderDetail(new OrderDetailKey(order.getId(), cartItem.getProductId()), order, productRepository.findById(cartItem.getProductId()).get(), cartItem.getQuantity()));
        }
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);

        // reset cart
        session.removeAttribute("cart");
    }

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
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Integer id, Order newOrder) {
        Order orderFound = orderRepository.findById(id).orElse(null);
        if (orderFound != null) {
            BeanUtils.copyProperties(newOrder, orderFound, "id");
            orderRepository.save(orderFound);
        }
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteByIdOfMy(id);
    }
}
