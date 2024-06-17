package rs.ac.bg.fon.ai.naprednoProgramiranje.actor;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.lang.NonNull;
import rs.ac.bg.fon.ai.naprednoProgramiranje.role.Role;

import java.util.Set;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActor;
    @NonNull
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;
    @NonNull
    @Size(min = 3, max = 20, message = "Lastname must be between 3 and 20 characters")
    private String lastName;
    @NonNull
    @Positive
    private int noOfOscars;
    @OneToMany(mappedBy = "idRole")
    private Set<Role> filmSet;





}
