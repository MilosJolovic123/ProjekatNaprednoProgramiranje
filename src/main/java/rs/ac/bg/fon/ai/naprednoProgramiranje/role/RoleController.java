package rs.ac.bg.fon.ai.naprednoProgramiranje.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("role/add/{filmId}/{actorId}")
    public ResponseEntity<Role> addRole(@RequestBody Role role, @PathVariable Long filmId, @PathVariable Long actorId){

        return ResponseEntity.ok(roleService.saveRole(role,filmId,actorId));
    }


}