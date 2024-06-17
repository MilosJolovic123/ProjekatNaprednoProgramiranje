package rs.ac.bg.fon.ai.naprednoProgramiranje.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Rest Controller ensuring our GET,POST,PUT and DELETE endpoints for Films.
 * @author milos jolovic
 */
@RestController
public class FilmController {

    /**
     * Private field - reference to the Service layer for Films.
     */
    private final FilmService filmService;

    /**
     * Constructor injection of film service dependency.
     * @param filmService reference to the film service dependency.
     */
    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }
    /**
     * Secured method for retrieving film from H2 db.
     * @param requestedId an id of a specific film to be returned.
     * @return requested film.
     */
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
    @GetMapping("film/{requestedId}")
    public ResponseEntity<FilmDTO> getFilm(@PathVariable("requestedId") Long requestedId) {
        return ResponseEntity.ok(filmService.getFilmById(requestedId));
    }
    /**
     * Secured method that gets all films persisted in H2 db.
     * @return List of all Films persisted.
     */
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
    @GetMapping("films")
    public ResponseEntity<List<FilmDTO>> getFilms() {
        return ResponseEntity.ok(filmService.getFilmList());
    }
    /**
     * Secured method for saving a film to H2 db.
     * @param film to be saved/persisted.
     * @param requestedDirector of a film to be saved.
     * @param requestedGenre of a film to be saved.
     * @return film passed as request body to be saved into H2 db.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("film/save/{requestedDirector}/{requestedGenre}")
    public ResponseEntity<Film> saveFilm(@RequestBody Film film, @PathVariable Long requestedDirector, @PathVariable Long requestedGenre) {
        return ResponseEntity.ok(filmService.saveFilm(film,requestedDirector,requestedGenre));
    }
    /**
     * Secured method for modifying the given film.
     * @param film object that carries new info to be given to a specific film.
     * @param requestedId id of a film to be modified.
     * @param requestedDirector new director.
     * @param requestedGenre new genre.
     * @return newly modified film.
     */
    @PreAuthorize("hasRole('ADMIN')")
     @PutMapping("film/update/{requestedId}/{requestedDirector}/{requestedGenre}")
    public ResponseEntity<Film> updateFilm(@RequestBody Film film, @PathVariable Long requestedId, @PathVariable Long requestedDirector, @PathVariable Long requestedGenre){
        return ResponseEntity.ok(filmService.updateFilm(film,requestedId,requestedDirector,requestedGenre));
    }
    /**
     * Secured method that deletes a specific film.
     * @param requestedId id of a film to be found in H2 and then deleted.
     * @return 200 OK status code .
     */
    @PreAuthorize("hasRole('ADMIN')")
     @DeleteMapping("film/delete/{requestedId}")
    public ResponseEntity<Integer> deleteFilm(@PathVariable Long requestedId){
        filmService.FilmDelete(requestedId);
        return ResponseEntity.ok(200);
    }
}