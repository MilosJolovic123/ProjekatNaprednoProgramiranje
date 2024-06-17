package rs.ac.bg.fon.ai.naprednoProgramiranje.film;

import lombok.*;

import java.sql.Date;
/**
 * DTO class for a film and representing it.
 * @author milos jolovic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilmDTO {
     /**
      * Date that the film was released.
      */
     private Date dateReleased;
     /**
      * Title of the film.
      */
     private String title;
     /**
      * Description of the film.
      */
     private String description;
     /**
      *Film land of origin.
      */
     private String landOfOrigin;
}
