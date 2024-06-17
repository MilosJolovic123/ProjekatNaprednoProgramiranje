package rs.ac.bg.fon.ai.naprednoProgramiranje.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmController {


    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
    @GetMapping("film/{requestedId}")
    public ResponseEntity<FilmDTO> getFilm(@PathVariable("requestedId") Long requestedId) {
        return ResponseEntity.ok(filmService.getFilmById(requestedId));
    }

    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
    @GetMapping("films")
    public ResponseEntity<List<FilmDTO>> getFilms() {
        return ResponseEntity.ok(filmService.getFilmList());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("film/save/{requestedDirector}/{requestedGenre}")
    public ResponseEntity<Film> saveFilm(@RequestBody Film film, @PathVariable Long requestedDirector, @PathVariable Long requestedGenre) {
        return ResponseEntity.ok(filmService.saveFilm(film,requestedDirector,requestedGenre));
    }

    @PreAuthorize("hasRole('ADMIN')")
     @PutMapping("film/update/{requestedId}/{requestedDirector}/{requestedGenre}")
    public ResponseEntity<Film> updateFilm(@RequestBody Film film, @PathVariable Long requestedId, @PathVariable Long requestedDirector, @PathVariable Long requestedGenre){
        return ResponseEntity.ok(filmService.updateFilm(film,requestedId,requestedDirector,requestedGenre));
    }
    @PreAuthorize("hasRole('ADMIN')")
     @DeleteMapping("film/delete/{requestedId}")
    public ResponseEntity<Integer> deleteFilm(@PathVariable Long requestedId){
        filmService.FilmDelete(requestedId);
        return ResponseEntity.ok(200);
    }
}