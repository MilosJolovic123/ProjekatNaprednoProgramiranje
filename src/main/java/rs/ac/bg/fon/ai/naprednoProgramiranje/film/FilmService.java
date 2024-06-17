package rs.ac.bg.fon.ai.naprednoProgramiranje.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.ai.naprednoProgramiranje.director.Director;
import rs.ac.bg.fon.ai.naprednoProgramiranje.director.DirectorRepository;
import rs.ac.bg.fon.ai.naprednoProgramiranje.genre.Genre;
import rs.ac.bg.fon.ai.naprednoProgramiranje.genre.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Service layer class for Film class. Ensures all CRUD ops with Film class and persisting it
 * in H2 database.
 * @author milos jolovic
 */
@Service
public class FilmService {
    /**
     * Private field - reference to FilmRepository class.
     */
    private final FilmRepository filmRepository;
    /**
     * Private field - reference to DirectorRepository class.
     */
    private final DirectorRepository directorRepository;
    /**
     * Private field - reference to GenreRepository class.
     */
    private final GenreRepository genreRepository;

    /**
     * Constructor injection for all the fields above.
     * @param filmRepository reference to the film repo.
     * @param directorRepository reference to the director repo.
     * @param genreRepository reference to the genre repo.
     */
    @Autowired
    public FilmService(FilmRepository filmRepository, DirectorRepository directorRepository, GenreRepository genreRepository) {
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
        this.genreRepository = genreRepository;
    }
    /**
     * Method that returns all Films persisted in H2 db.
     * @return List of all Films as a DTO class.
     */
    public List<FilmDTO> getFilmList() {

        List<Film> films = filmRepository.findAll();

        List<FilmDTO> filmsDTOs = new ArrayList<>();

        for(Film f:films){
            FilmDTO filmDto = new FilmDTO();
            filmDto.setDescription(f.getDescription());
            filmDto.setDateReleased(f.getDateReleased());
            filmDto.setTitle(f.getTitle());
            filmDto.setLandOfOrigin(f.getLandOfOrigin());
            filmsDTOs.add(filmDto);
        }

        return filmsDTOs;
    }
    /**
     * Method that searches db for a specific film through id value.
     * @param id of a specific film to be returned.
     * @throws RuntimeException if the specific film is not to be found in H2.
     * @return Film with film_id id.
     */
    public FilmDTO getFilmById(Long id) {
        Optional<Film> film=filmRepository.findById(id);
        if(film.isEmpty()){
            throw new RuntimeException("Film not found!");
        }
        FilmDTO filmDto = new FilmDTO();
        filmDto.setDescription(film.get().getDescription());
        filmDto.setDateReleased(film.get().getDateReleased());
        filmDto.setTitle(film.get().getTitle());
        filmDto.setLandOfOrigin(film.get().getLandOfOrigin());
        return filmDto;
    }
    /**
     * Method that enables persistence of the film in H2 db.
     * Not adding any validation to dates because of Films that have a Release Date in the future.
     * (that are to be released)
     * @param film to be saved.
     * @param requestedDirector director of a film to be saved.
     * @param requestedGenre genre of a film to be saved.
     * @throws RuntimeException if director or genre are not to be found.
     * @return the film to be saved in H2 db.
     */
    public Film saveFilm(Film film,Long requestedDirector, Long requestedGenre) {

        Optional<Director> directorOptional = directorRepository.findById(requestedDirector);
        Optional<Genre> genreOptional = genreRepository.findById(requestedGenre);

        if(directorOptional.isEmpty()||genreOptional.isEmpty())
            throw new RuntimeException("Director or genre not present in H2!");

        film.setFilm_director(directorOptional.get());
        film.setFilm_genre(genreOptional.get());

        return filmRepository.save(film);
    }
    /**
     * Method that ensures updates to be carried on a film object.
     * @param film that carries new info.
     * @param requestedFilm to be modified.
     * @param requestedDirector new director value.
     * @param requestedGenre new genre value.
     * @throws RuntimeException if director, film or genre are not present.
     * @return modified film.
     */
    public Film updateFilm(Film film,Long requestedFilm,Long requestedDirector, Long requestedGenre){
        Optional<Director> directorOptional = directorRepository.findById(requestedDirector);
        Optional<Genre> genreOptional = genreRepository.findById(requestedGenre);
        Optional<Film> filmToUpdate = filmRepository.findById(requestedFilm);

        if(directorOptional.isEmpty()||genreOptional.isEmpty()||filmToUpdate.isEmpty())
            throw new RuntimeException("The provided director, genre, or film to be updated don't exist in H2 db!");

        filmToUpdate.get().setFilm_director(directorOptional.get());
        filmToUpdate.get().setFilm_genre(genreOptional.get());
        filmToUpdate.get().setDateReleased(film.getDateReleased());
        filmToUpdate.get().setRoleSet(film.getRoleSet());
        filmToUpdate.get().setDescription(film.getDescription());
        filmToUpdate.get().setLandOfOrigin(film.getLandOfOrigin());
        filmToUpdate.get().setTitle(film.getTitle());
        filmToUpdate.get().setReviews(film.getReviews());

        return filmRepository.save(filmToUpdate.get());
    }
    /**
     * deleted film
     * @param requestedId of a specific film to be found in H2 db and then deleted.
     */
    public void FilmDelete(Long requestedId){
        filmRepository.deleteById(requestedId);
    }
}



