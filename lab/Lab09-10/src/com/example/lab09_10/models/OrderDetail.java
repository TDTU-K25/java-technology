package com.example.lab09_10.models;

import com.example.lab09_10.models.PK.OrderDetailKey;
import jakarta.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailKey id;

    @JoinColumn(name = "order_id")
    @MapsId("orderId")
    @ManyToOne
    private Order order;

    @JoinColumn(name = "product_id")
    @MapsId("productId")
    @ManyToOne
    private Product product;

    private int quantity;
}
