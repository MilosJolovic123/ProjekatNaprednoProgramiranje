package rs.ac.bg.fon.ai.naprednoProgramiranje.actor;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.lang.NonNull;
import rs.ac.bg.fon.ai.naprednoProgramiranje.role.Role;

import java.util.Set;
/**
 * Actor domain class that encapsulates the usage of Actors in Films for the platform.
 * It is mapped out in a table Actor in H2 in-memory database.
 * @author milos jolovic
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Actor {
    /**
     * Unique generated identification value for Actor objects both in API and db usage.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActor;
    /**
     * Name of an Actor represented like a String value.
     * Can't be longer than 20 characters or shorter than 3 characters, can't be null also.
     */
    @NonNull
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;
    /**
     * Lastname of an Actor represented like a String value.
     * Can't be longer than 20 characters or shorter than 3 characters, can't be null also.
     */
    @NonNull
    @Size(min = 3, max = 20, message = "Lastname must be between 3 and 20 characters")
    private String lastName;
    @NonNull
    @Positive
    /**
     * Number of Oscar awards that Actor gained as an Integer.
     * Can't be less than 0.
     */
    private int noOfOscars;
    @OneToMany(mappedBy = "idRole")
    private Set<Role> filmSet;





}
