package rs.ac.bg.fon.ai.naprednoProgramiranje.director;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface that extends @JpaRepository and ensures all CRUD ops with Director.
 * @author milos jolovic
 */
@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}
