package rs.ac.bg.fon.ai.naprednoProgramiranje.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import rs.ac.bg.fon.ai.naprednoProgramiranje.newsletter.Newsletter;
import rs.ac.bg.fon.ai.naprednoProgramiranje.review.Review;

import java.util.Set;
/**
 * AppUser class that maps into AppUser table in H2.
 * @author milos jolovic
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "AppUser")
public class AppUser {
    /**
     * Unique generated value that serves as a identifier to AppUser class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    /**
     * Name and last Name of a user.
     * Must be a non-null value and between 7 and 50 chars long.
     */
    @NonNull
    @Size(min = 7, max = 50,message = "Actor name and lastname must be between 7 and 50 chars long.")
    private String NameAndLastName;
    /**
     * Username of a user.
     * Must be a non-null value and between 7 and 60 chars long.
     */
    @NonNull
    @Size(min = 7, max =60, message = "Username must be between 7 and 60 chars long.")
    private String username;
    /**
     * Password of a user.
     * Must be a non-null value and between 7 and 60 chars long.
     */
    @NonNull
    @Size(min = 7, max = 60, message = "Password must be between 7 and 60 chars long.")
    private String password;
    /**
     * Roles that user can have - ROLE_USER and ROLE_ADMIN
     */
    @NonNull
    private String roles;
    /**
     * Reference to the user newsletters.
     */
    @OneToMany(mappedBy = "newsletter_user")
    private Set<Newsletter> newsletterSet;
    /**
     * Reference to the user reviews.
     */
    @OneToMany(mappedBy = "idReview")
    private Set<Review> reviewSet;
    /**
     * Constructor for Users.
     * @param NameAndLastName of the user.
     * @param username of the user.
     * @param password of the user.
     * @param roles that user can have.
     */
    public AppUser(String NameAndLastName,String username, String password, String roles) {
        this.NameAndLastName = NameAndLastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

}
