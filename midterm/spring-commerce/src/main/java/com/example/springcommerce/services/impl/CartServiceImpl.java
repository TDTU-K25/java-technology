package com.example.springcommerce.services.impl;

import com.example.springcommerce.DTO.CartItem;
import com.example.springcommerce.models.Product;
import com.example.springcommerce.repository.ProductRepository;
import com.example.springcommerce.services.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    HttpSession session;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void add(int productId) {
        Product productFound = productRepository.findById(productId).get();
        Map<Integer, CartItem> cart = (HashMap<Integer, CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        if (cart.get(productId) == null) {
            CartItem cartItem = new CartItem(productId);
            cartItem.setQuantity(1);
            cartItem.setTotalPrice(productFound.getPrice());
            cartItem.setName(productFound.getName());
            cartItem.setPrice(productFound.getPrice());
            cartItem.setImg(productFound.getIllustration());
            cart.put(productId, cartItem);
        } else {
            CartItem cartItem = cart.get(productId);
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItem.setTotalPrice(productFound.getPrice() * cartItem.getQuantity());
        }

        System.out.println(cart);
        session.setAttribute("cart", cart);
    }

    @Override
    public void clear() {
        session.removeAttribute("cart");
        System.out.println(session.getAttribute("cart"));
    }

    @Override
    public void update(int productId, int quantity) {
        Product productFound = productRepository.findById(productId).get();
        Map<Integer, CartItem> cart = (HashMap<Integer, CartItem>) session.getAttribute("cart");
        CartItem cartItem = cart.get(productId);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(productFound.getPrice() * cartItem.getQuantity());
        System.out.println(cart);
    }

    @Override
    public void remove(int productId) {
        Map<Integer, CartItem> cart = (HashMap<Integer, CartItem>) session.getAttribute("cart");
        cart.remove(productId);
        session.setAttribute("cart", cart);
        System.out.println(cart);
    }
}
