package rs.ac.bg.fon.ai.naprednoProgramiranje.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActorController {

    private final ActorService actorService;

     @Autowired
     public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actor/save")
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor){
        return ResponseEntity.ok(actorService.saveActor(actor));
    }



}
