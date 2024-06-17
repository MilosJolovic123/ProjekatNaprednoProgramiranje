package rs.ac.bg.fon.ai.naprednoProgramiranje.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
/**
 * Security User - User Detail service that handles logged user, authorization and authentication.
 * @author milos jolovic
 */
public class SecurityUser implements UserDetails {
    /**
     * reference to the user
     */
    private final AppUser user;
    /**
     * Constructor injection of AppUser.
     * @param user that is to be injected.
     */
    public SecurityUser(AppUser user) {
        this.user = user;
    }
    /**
     * Method for getting all roles of a user.
     * @return List of user roles.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(
                        user
                                .getRoles()
                                .split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

    }
    /**
     * Password getter
     * @return user password.
     */
     @Override
    public String getPassword() {
        return user.getPassword();
    }
    /**
     * Username getter.
     * @return user username.
     */
     @Override
    public String getUsername() {
        return user.getUsername();
    }
}

