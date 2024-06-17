package rs.ac.bg.fon.ai.naprednoProgramiranje.film;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilmDTO {
     private Date dateReleased;
     private String title;
     private String description;
     private String landOfOrigin;
}
