package rs.ac.bg.fon.ai.naprednoProgramiranje.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {


    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor saveActor(Actor actor) {

        if (actor == null)
            throw new NullPointerException("Null actor!");

        return actorRepository.save(actor);
    }
}
