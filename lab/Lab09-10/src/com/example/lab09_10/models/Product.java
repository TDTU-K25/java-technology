package com.example.lab09_10.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;
    private String productName;
    private Double price;
    private String illustration; // image
    private String description;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails = new ArrayList<>();
}
