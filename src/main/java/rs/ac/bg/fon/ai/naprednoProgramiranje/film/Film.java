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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFilm;
    @NonNull
    private Date dateReleased;
    @NonNull
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;
    @NonNull
    @Size(min = 10, max = 500, message = "Description must be between 0 and 500 characters")
    private String description;
    @NonNull
    @Size(min = 5, max = 50, message = "Land of origin must be between 5 and 50 characters")
    private String landOfOrigin;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idDirector")
    private Director film_director;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name ="idGenre")
    private Genre film_genre;
    @OneToMany(mappedBy = "idRole")
    private Set<Role> roleSet;
    @OneToMany(mappedBy = "idReview")
    private Set<Review> reviews;


}
