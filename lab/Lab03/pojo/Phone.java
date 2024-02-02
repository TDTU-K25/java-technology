package org.example.pojo;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MobilePhone")
public class Phone {
    @Id
    public String id;
    @NotNull
    @Size(min = 3, max = 128)
    public String name;
    @Column(nullable = false)
    public int price;
    @NotNull
    public String color;
    public String country;
    public int quantity;
    @ManyToOne()
    @JoinColumn(name = "manufacture_id")
    public Manufacture manufacture;

    @Override
    public String toString() {
        return "Phone{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", country='" + country + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
