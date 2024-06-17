package rs.ac.bg.fon.ai.naprednoProgramiranje.newsletter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.AppUser;

import java.sql.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Newsletter")
public class Newsletter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsletter_id;
    @NonNull
    private Date newsletter_date;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="id_user")
    private AppUser newsletter_user;
}
