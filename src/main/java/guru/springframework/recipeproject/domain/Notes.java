package guru.springframework.recipeproject.domain;


import lombok.*;

import javax.persistence.*;


@Entity
@EqualsAndHashCode(exclude = {"recipe"})
@Getter
@Setter
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

}
