package rs.ac.bg.fon.ai.naprednoProgramiranje.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Rest Controller ensuring all HTTP requests and endpoints for AppUser class.
 */
@RestController("")
public class UserController {
    final UserService userService;

     @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{requiredId}")
    public ResponseEntity<AppUserDTO> getUser(@PathVariable Long requiredId) {
        Optional<AppUserDTO> user = Optional.ofNullable(userService.getUserById(requiredId));
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public List<AppUserDTO> getUsers() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users/add")
    public ResponseEntity<AppUser> addUser(@RequestBody AppUser appUser) {
        return ResponseEntity.ok(userService.createUser(appUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/update/{requestedId}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Long requestedId, @RequestBody AppUser appUser) {
        return ResponseEntity.ok(userService.updateUser(appUser, requestedId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/delete/{requestedId}")
    public ResponseEntity<Integer> deleteUser(@PathVariable Long requestedId) {
        userService.deleteUser(requestedId);
        return ResponseEntity.ok(200);
    }

}

