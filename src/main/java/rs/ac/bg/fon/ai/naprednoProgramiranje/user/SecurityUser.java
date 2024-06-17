package rs.ac.bg.fon.ai.naprednoProgramiranje.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class SecurityUser implements UserDetails {

    private final AppUser user;

    public SecurityUser(AppUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(
                        user
                                .getRoles()
                                .split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

    }

     @Override
    public String getPassword() {
        return user.getPassword();
    }

     @Override
    public String getUsername() {
        return user.getUsername();
    }
}

