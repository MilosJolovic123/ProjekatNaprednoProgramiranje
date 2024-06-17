package rs.ac.bg.fon.ai.naprednoProgramiranje.director;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.lang.NonNull;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.Film;

import java.util.Set;
/**
 *  * Director domain class that encapsulates the usage of Directors of  Films for the platform.
 *  * It is mapped out in a table Director in H2 in-memory database.
 * @author milos jolovic
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Director {
    /**
     * Generated value - unique identifier of Director class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDirector;
    /**
     * Name of a Director represented as a String value.
     * It can't be less than 3 or more than 20 chars, can't be null also.
     */
    @NonNull
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;
    /**
     * Lastname of a Director represented as a String value.
     * It can't be less than 3 or more than 20 chars, can't be null also.
     */
    @NonNull
    @Size(min = 3, max = 20, message = "Lastname must be between 3 and 20 characters")
    private String lastName;
    /**
     * No. of Oscars a Director has represented as  integer value. Must be positive
     */
    @Positive
    private int noOfOscars;
    @OneToMany(mappedBy = "film_director")
    private Set<Film> filmSet;
}
