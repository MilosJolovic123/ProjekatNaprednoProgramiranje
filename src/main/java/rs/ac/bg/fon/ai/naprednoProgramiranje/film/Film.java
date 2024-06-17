package rs.ac.bg.fon.ai.naprednoProgramiranje.film;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.lang.NonNull;
import rs.ac.bg.fon.ai.naprednoProgramiranje.director.Director;
import rs.ac.bg.fon.ai.naprednoProgramiranje.genre.Genre;
import rs.ac.bg.fon.ai.naprednoProgramiranje.review.Review;
import rs.ac.bg.fon.ai.naprednoProgramiranje.role.Role;

import java.sql.Date;
import java.util.Set;
/**
 *  * Film domain class that encapsulates the usage of Films for the platform.
 *  * It is mapped out in a table Film in H2 in-memory database.
 * @author milos jolovic
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Film {
    /**
     * Generated value - unique identifier of Film class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFilm;
    /**
     * Date film was released. It must be non-null value.
     */
    @NonNull
    private Date dateReleased;
    /**
     * Title of the Film represented as a String value. Must be non-null value and
     * between 3 and 50 chars long.
     */
    @NonNull
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;
    /**
     *  Description of the Film represented as a String value. Must be a non-null value
     *  and between 10 and 500 chars long.
     */
    @NonNull
    @Size(min = 10, max = 500, message = "Description must be between 0 and 500 characters")
    private String description;
    /**
     * Film land of origin represented as a String value.
     * Must be a non-null value
     *and between 5 and 50 chars long.
     */
    @NonNull
    @Size(min = 5, max = 50, message = "Land of origin must be between 5 and 50 characters")
    private String landOfOrigin;
    /**
     * Reference to the film Director.
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idDirector")
    private Director film_director;
    /**
     * Reference to roles of actors in a film.
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name ="idGenre")
    private Genre film_genre;
    /**
     * Reference to the roles of the films.
     */
    @OneToMany(mappedBy = "idRole")
    private Set<Role> roleSet;
    /**
     * Reference to the reviews of the films.
     */
    @OneToMany(mappedBy = "idReview")
    private Set<Review> reviews;


}
