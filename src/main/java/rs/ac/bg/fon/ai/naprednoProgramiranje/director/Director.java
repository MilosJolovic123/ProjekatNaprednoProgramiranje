package rs.ac.bg.fon.ai.naprednoProgramiranje.director;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.lang.NonNull;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.Film;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDirector;
    @NonNull
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;
    @NonNull
    @Size(min = 3, max = 20, message = "Lastname must be between 3 and 20 characters")
    private String lastName;
    @Positive
    private int noOfOscars;
    @OneToMany(mappedBy = "film_director")
    private Set<Film> filmSet;
}
