package rs.ac.bg.fon.ai.naprednoProgramiranje.director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer class for business logic of working with Director class.
 * @author milos jolovic
 */
@Service
public class DirectorService {
    /**
     * Private final field for referencing director repo.
     */
    private final DirectorRepository directorRepository;

    /**
     * Constructor injection of director repo.
     * @param directorRepository reference to the specific repo.
     */
    @Autowired
    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    /**
     * Method for saving the director in H2 db.
     * @param director to be saved.
     * @return saved director.
     */
    public Director saveDirector(Director director) {
        if(director == null)
            throw new NullPointerException("Director is null!");
        return directorRepository.save(director);
    }



}
