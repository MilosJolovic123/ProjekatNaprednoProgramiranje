package rs.ac.bg.fon.ai.naprednoProgramiranje.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer of Genre class where all business logic is implemented.
 */
@Service
public class GenreService {
    final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

}