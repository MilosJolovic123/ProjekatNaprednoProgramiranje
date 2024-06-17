package rs.ac.bg.fon.ai.naprednoProgramiranje.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * Rest controller that enables POST endpoint for saving role in H2 db.
 * @author milos jolovic
 */
@RestController
public class RoleController {
    /**
     * Private field, reference to the RoleService.
     */
    private final RoleService roleService;

    /**
     * Constructor injection of role Service dependency.
     * @param roleService reference to the specific role service.
     */
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    /**
     * Secured method for saving a Role in H2 db.
     * @param role to be saved.
     * @param filmId in which film is the given role.
     * @param actorId which actor acts the role.
     * @return value of the role.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("role/add/{filmId}/{actorId}")
    public ResponseEntity<Role> addRole(@RequestBody Role role, @PathVariable Long filmId, @PathVariable Long actorId){

        return ResponseEntity.ok(roleService.saveRole(role,filmId,actorId));
    }


}