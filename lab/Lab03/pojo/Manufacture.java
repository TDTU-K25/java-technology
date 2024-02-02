package org.example.pojo;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Manufacture")
public class Manufacture {
    @Id
    public String id;
    @Size(min = 3, max = 128)
    public String name;
    public String location;
    public int employee;
    @OneToMany(mappedBy = "manufacture", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Phone> phoneList;

    @Override
    public String toString() {
        return "Manufacture{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", employee=" + employee +
                '}';
    }
}
