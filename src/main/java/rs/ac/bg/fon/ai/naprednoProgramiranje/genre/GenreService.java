package rs.ac.bg.fon.ai.naprednoProgramiranje.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer of Genre class where all business logic is implemented.
 * @author milos jolovic
 */
@Service
public class GenreService {
    /**
     * Field that is a reference to the GenreRepository class.
     */
    final GenreRepository genreRepository;
    /**
     * Constructor injection of GenreRepository.
     * @param genreRepository specific genre repo reference.
     */
    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    /**
     * Method that saves specific Genre to H2 db.
     * @param genre that is to be saved.
     * @return genre to be saved.
     */
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

}