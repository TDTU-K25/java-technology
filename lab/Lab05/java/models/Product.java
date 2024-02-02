package models;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Length(min = 5, max = 50)
	private String name;

	@Range(min = 0)
	private int price;

	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}
}
