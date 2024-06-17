package rs.ac.bg.fon.ai.naprednoProgramiranje.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * Rest Controller ensuring our POST endpoint for saving actors in H2 database.
 * @author milos jolovic
 */
@RestController
public class ActorController {
    /**
     * Private field that ensures reference to Service class which is business logic for
     * saving Actors in H2 db.
     */
    private final ActorService actorService;

    /**
     * Constructor injection of dependency actorService.
     * @param actorService the reference to the specific actor service.
     */
     @Autowired
     public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }
    /**
     * A HTTP POST method that ensures adding an actor passed through JSON request body in H2 db.
     * @param actor defined in HTTP method body
     * @return ResponseEntity, that is, an actor we are saving in db.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actor/save")
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor){
        return ResponseEntity.ok(actorService.saveActor(actor));
    }



}
