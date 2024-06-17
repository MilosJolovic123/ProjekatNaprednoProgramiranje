package rs.ac.bg.fon.ai.naprednoProgramiranje.UserDetailService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.SecurityUser;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.UserRepository;
/**
 * Service layer that handles Security.
 * @author milos jolovic
 */
@Service
public class JpaUserDetailsService implements UserDetailsService {
    /**
     * Reference to the User repo.
     */
 private final UserRepository userRepository;
    /**
     * Constructor injection of User repo.
     * @param userRepository reference to the User repo.
     */
    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     * Method for loading a new SecurityUser by username.
     * @param username for loading the SecurityUser.
     * @return SecurityUser
     * @throws UsernameNotFoundException when user by given username is not found in H2.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found: "+username));
    }
}