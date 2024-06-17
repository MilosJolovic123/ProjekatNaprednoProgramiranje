package rs.ac.bg.fon.ai.naprednoProgramiranje.genre;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GenreController {
    final private GenreService genreService;

     @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
    @PreAuthorize("hasRole('ADMIN')")
   @PostMapping("genre/save")
    public ResponseEntity<Genre> addGenre(@RequestBody Genre genre) {
        return ResponseEntity.ok(genreService.saveGenre(genre));
    }

}

