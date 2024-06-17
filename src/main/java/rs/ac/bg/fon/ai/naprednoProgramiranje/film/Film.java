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

    private Date dateReleased;

    private String title;

    private String description;
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
