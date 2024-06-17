package rs.ac.bg.fon.ai.naprednoProgramiranje.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Rest Controller ensuring all HTTP requests and endpoints for AppUser class.
 * @author milos jolovic
 */
@RestController("")
public class UserController {
    /**
     * Private field that is a direct reference to UserService.
     */
    final UserService userService;

    /**
     * Constructor injection of user service dependency.
     * @param userService reference to the user service.
     */
     @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    /**
     * GET method for user.
     * @param requiredId of a user to be retrieved.
     * @return User to be retrieved.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{requiredId}")
    public ResponseEntity<AppUserDTO> getUser(@PathVariable Long requiredId) {
        Optional<AppUserDTO> user = Optional.ofNullable(userService.getUserById(requiredId));
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    /**
     * GET method for all users.
     * @return List of all users.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public List<AppUserDTO> getUsers() {
        return userService.getAllUsers();
    }
    /**
     * POST method for a User.
     * @param appUser user to be saved.
     * @return User that is saved.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users/add")
    public ResponseEntity<AppUser> addUser(@RequestBody AppUser appUser) {
        return ResponseEntity.ok(userService.createUser(appUser));
    }
    /**
     * PUT method for a User.
     * @param requestedId of a User to be updated.
     * @param appUser user that carries changes of info.
     * @return newly updated User.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/update/{requestedId}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Long requestedId, @RequestBody AppUser appUser) {
        return ResponseEntity.ok(userService.updateUser(appUser, requestedId));
    }
    /**
     * DELETE for User.
     * @param requestedId of a user to be deleted.
     * @return 200 OK status code.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/delete/{requestedId}")
    public ResponseEntity<Integer> deleteUser(@PathVariable Long requestedId) {
        userService.deleteUser(requestedId);
        return ResponseEntity.ok(200);
    }

}

