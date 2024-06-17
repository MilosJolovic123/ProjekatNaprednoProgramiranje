package rs.ac.bg.fon.ai.naprednoProgramiranje.director;

import jakarta.persistence.*;
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
    private String name;
    private String lastName;
    private int noOfOscars;
    @OneToMany(mappedBy = "film_director")
    private Set<Film> filmSet;
}
