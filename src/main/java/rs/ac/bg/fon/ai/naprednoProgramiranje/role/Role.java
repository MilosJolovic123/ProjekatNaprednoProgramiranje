package rs.ac.bg.fon.ai.naprednoProgramiranje.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import rs.ac.bg.fon.ai.naprednoProgramiranje.actor.Actor;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.Film;
/**
 * Role domain class that is associated to an Actor that acts it. It's mapped
 * to Role table in H2 db.
 * @author milos jolovic
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    /**
     * Unique generated value that serves as an identifier to Role class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;
    /**
     * Role name represented as a String. Must be a non-null value and
     * between 5 and 50 chars long.
     */
    @NonNull
    @Size(min = 5, max = 50, message = "Role name must be between 5 and 50 characters!")
    private String roleName;
    /**
     * Description of a role represented as a String. Must be a non-null value and
     * between 5 and 50 chars long.
     */
    @NonNull
    @Size(min = 5, max = 50, message = "Description must be between 5 and 50 characters!")
    private String description;
    /**
     * Actor that acts the role, direct reference to the Actor table in H2 db.
     */
    @ManyToOne
    @JoinColumn(name="idActor")
    @JsonIgnore
    private Actor actor;
    /**
     * Movie that has this role, direct reference to the Movie table in H2 db.
     */
    @ManyToOne
    @JoinColumn(name="idFilm")
    @JsonIgnore
    private Film film;
}


