package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Length(min = 1, max = 25)
	private String name;

	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", name='" + name + '\'' +
			", email='" + email + '\'' +
			", password='" + password + '\'' +
			'}';
	}
}
