package rs.ac.bg.fon.ai.naprednoProgramiranje.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import rs.ac.bg.fon.ai.naprednoProgramiranje.newsletter.Newsletter;
import rs.ac.bg.fon.ai.naprednoProgramiranje.review.Review;

import java.util.Set;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "AppUser")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    @NonNull
    @Size(min = 7, max = 50,message = "Actor name and lastname must be between 7 and 50 chars long.")
    private String NameAndLastName;
    @NonNull
    @Size(min = 7, max =60, message = "Username must be between 7 and 60 chars long.")
    private String username;
    @NonNull
    @Size(min = 7, max = 60, message = "Password must be between 7 and 60 chars long.")
    private String password;
    @NonNull
    private String roles;
    @OneToMany(mappedBy = "newsletter_user")
    private Set<Newsletter> newsletterSet;
    @OneToMany(mappedBy = "idReview")
    private Set<Review> reviewSet;

    public AppUser(String NameAndLastName,String username, String password, String roles) {
        this.NameAndLastName = NameAndLastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
