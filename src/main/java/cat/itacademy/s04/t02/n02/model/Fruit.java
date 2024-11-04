package cat.itacademy.s04.t02.n02.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is a required field")
    @Column(unique = true)
    private String name;

    @NotNull(message = "quantity is a required field")
    @PositiveOrZero(message = "quantity can't be negative")
    private Double quantityKilograms;

}
