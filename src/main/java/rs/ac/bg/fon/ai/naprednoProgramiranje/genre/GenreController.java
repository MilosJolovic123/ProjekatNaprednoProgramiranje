package rs.ac.bg.fon.ai.naprednoProgramiranje.genre;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
/**
 * Rest Controller that ensures POST method of a Genre class.
 * @author milos jolovic
 */
@RestController
public class GenreController {
    /**
     * Reference to the GenreService class.
     */
    final private GenreService genreService;
    /**
     * Constructor injection of a GenreService class
     * @param genreService specific genreService class to be referenced.
     */
     @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
    /**
     * Secured method for saving a genre passed through POST request body.
     * @param genre to be saved.
     * @return genre to be saved.
     */
    @PreAuthorize("hasRole('ADMIN')")
   @PostMapping("genre/save")
    public ResponseEntity<Genre> addGenre(@RequestBody Genre genre) {
        return ResponseEntity.ok(genreService.saveGenre(genre));
    }

}

