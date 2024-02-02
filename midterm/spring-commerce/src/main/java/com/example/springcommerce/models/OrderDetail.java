package com.example.springcommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "order_detail")
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @JsonIgnore
    @EmbeddedId
    private OrderDetailKey id;

    @JsonIgnore
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