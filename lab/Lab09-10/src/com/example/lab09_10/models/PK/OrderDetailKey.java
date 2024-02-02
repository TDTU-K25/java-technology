package com.example.lab09_10.models.PK;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderDetailKey implements Serializable {
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "product_id")
    private int productId;
}
