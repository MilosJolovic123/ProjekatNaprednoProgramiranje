package rs.ac.bg.fon.ai.naprednoProgramiranje.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.lang.NonNull;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.Film;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.AppUser;

import java.sql.Date;
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    /**
     * Unique generated value that is identifier for Review class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReview;
    private String comment;
    private int grade;

    private Date dateGiven;
    @ManyToOne
    @JoinColumn(name="idFilm")
    @JsonIgnore
    private Film film;
    @ManyToOne
    @JoinColumn(name="id_user")
    @JsonIgnore
    private AppUser user;


}
