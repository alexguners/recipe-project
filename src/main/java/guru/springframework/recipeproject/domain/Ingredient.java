package guru.springframework.recipeproject.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode(exclude = {"recipe"})
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeassure;

    @ManyToOne
    private Recipe recipe;

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeassure) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeassure = unitOfMeassure;
    }

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeassure, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeassure = unitOfMeassure;
        this.recipe = recipe;
    }
}
