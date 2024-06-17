package rs.ac.bg.fon.ai.naprednoProgramiranje.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Repo that extends JpaRepository and ensures all CRUD ops for AppUser.
 * @author milos jolovic
 */
@Repository
public interface UserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByUsername(String username);
}
