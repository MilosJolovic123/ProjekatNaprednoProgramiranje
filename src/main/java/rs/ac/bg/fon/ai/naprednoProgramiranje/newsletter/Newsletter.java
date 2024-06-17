package rs.ac.bg.fon.ai.naprednoProgramiranje.newsletter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.AppUser;

import java.sql.Date;
/**
 * Newsletter domain class associated to a specific User that applies for a Newsletter.
 * Mapped out in Newsletter table in H2 db.
 * @author milos jolovic
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Newsletter")
public class Newsletter {
    /**
     * Unique generated Long value that represents identifier to Newsletter class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsletter_id;
    /**
     * Date of applying to newsletter. Can't be null.
     */
    @NonNull
    private Date newsletter_date;
    /**
     * User that is the owner of newsletter application, direct reference to the User table
     * in H2 db.
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="id_user")
    private AppUser newsletter_user;
}
