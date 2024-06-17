package rs.ac.bg.fon.ai.naprednoProgramiranje.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.ai.naprednoProgramiranje.actor.Actor;
import rs.ac.bg.fon.ai.naprednoProgramiranje.actor.ActorRepository;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.Film;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.FilmRepository;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final FilmRepository filmRepository;
    private final ActorRepository actorRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, FilmRepository filmRepository, ActorRepository actorRepository) {
        this.roleRepository = roleRepository;
        this.filmRepository = filmRepository;
        this.actorRepository = actorRepository;
    }

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
