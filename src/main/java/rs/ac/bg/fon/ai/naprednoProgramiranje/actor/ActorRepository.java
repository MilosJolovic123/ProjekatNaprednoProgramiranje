package rs.ac.bg.fon.ai.naprednoProgramiranje.actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface that extends JPA interface for all CRUD ops of @Actor class.
 * @author milos jolovic
 */
@Repository
public interface ActorRepository extends JpaRepository<Actor,Long> {
}
