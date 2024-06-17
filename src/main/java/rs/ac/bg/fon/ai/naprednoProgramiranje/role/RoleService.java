package rs.ac.bg.fon.ai.naprednoProgramiranje.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.ai.naprednoProgramiranje.actor.Actor;
import rs.ac.bg.fon.ai.naprednoProgramiranje.actor.ActorRepository;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.Film;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.FilmRepository;

import java.util.Optional;
/**
 * Service layer class that handles all business logic on Role class.
 * @author milos jolovic
 */
@Service
public class RoleService {
    /**
     * Private field that is a reference to role repo.
     */
    private final RoleRepository roleRepository;
    /**
     * Private field that is a reference to film repo.
     */
    private final FilmRepository filmRepository;
    /**
     * Private field that is a reference to actor repo.
     */
    private final ActorRepository actorRepository;

    /**
     * Constructor injection of the above defined dependencies.
     * @param roleRepository reference to the specific role repo.
     * @param filmRepository reference to the specific film repo.
     * @param actorRepository reference to the specific actor repo.
     */
    @Autowired
    public RoleService(RoleRepository roleRepository, FilmRepository filmRepository, ActorRepository actorRepository) {
        this.roleRepository = roleRepository;
        this.filmRepository = filmRepository;
        this.actorRepository = actorRepository;
    }
    /**
     * Method for saving the Role.
     * @param role to be saved.
     * @param filmId to be saved onto.
     * @param actorId which acted that role.
     * @throws RuntimeException if provided film of actor does not exist in H2.
     * @return role saved.
     */
    public Role saveRole(Role role, Long filmId, Long actorId){

        Optional<Film> film = filmRepository.findById(filmId);
        Optional<Actor> actor = actorRepository.findById(actorId);
        if(film.isEmpty() || actor.isEmpty())
            throw new RuntimeException("Provided actor or film does not exist in H2.");
        role.setFilm(film.get());
        role.setActor(actor.get());

        return roleRepository.save(role);
    }

}
