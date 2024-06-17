package rs.ac.bg.fon.ai.naprednoProgramiranje.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import rs.ac.bg.fon.ai.naprednoProgramiranje.actor.Actor;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.Film;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;
    private String roleName;
    private String description;

    @ManyToOne
    @JoinColumn(name="idActor")
    @JsonIgnore
    private Actor actor;

    @ManyToOne
    @JoinColumn(name="idFilm")
    @JsonIgnore
    private Film film;
}