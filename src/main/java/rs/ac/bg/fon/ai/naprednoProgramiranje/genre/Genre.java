package rs.ac.bg.fon.ai.naprednoProgramiranje.genre;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.Film;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idGenre;
    private String genreDesctiption;
    @OneToMany(mappedBy = "film_genre")
    private Set<Film> filmSet;
}
