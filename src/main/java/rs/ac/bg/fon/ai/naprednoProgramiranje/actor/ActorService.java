package rs.ac.bg.fon.ai.naprednoProgramiranje.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service layer class for our Actors. Here lies the business logic of adding actors to H2 db.
 * @author milos jolovic
 */
@Service
public class ActorService {

    /**
     * private field that ensures a reference to @ActorRepository class.
     */
    private final ActorRepository actorRepository;

    /**
     * Constructor injection for actorRepository dependency.
     * @param actorRepository a reference to the specific actor repo.
     */
    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }
    /**
     * Method that saves passed @Actor in H2 db.
     * @throws IllegalArgumentException if NO. of Oscar awards is less then 0.
     * @throws NullPointerException if passed Actor is null.
     * @param actor to be passed to H2 db for persisting it.
     * @return @Actor that we are passing through JSON and saving in H2 db.
     */
    public Actor saveActor(Actor actor) {

        if (actor == null)
            throw new NullPointerException("Null actor!");

        return actorRepository.save(actor);
    }
}
