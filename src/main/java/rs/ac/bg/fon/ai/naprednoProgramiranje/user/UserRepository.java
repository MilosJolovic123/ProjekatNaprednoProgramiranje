package rs.ac.bg.fon.ai.naprednoProgramiranje.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser,Long> {
}
