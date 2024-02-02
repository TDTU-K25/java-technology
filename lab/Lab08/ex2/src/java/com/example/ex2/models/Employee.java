package com.example.ex2.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String address;
    private String phone;
}
