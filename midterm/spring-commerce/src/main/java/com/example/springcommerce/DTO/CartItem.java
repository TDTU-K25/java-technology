package com.example.springcommerce.DTO;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@RequiredArgsConstructor
public class CartItem implements Serializable {
    @NonNull
    private int productId;
    private String name;
    private Double price;
    private int quantity;
    private double totalPrice;
    private String img;
}
