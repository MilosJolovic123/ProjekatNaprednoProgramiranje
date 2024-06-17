package rs.ac.bg.fon.ai.naprednoProgramiranje.newsletter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface that extends JpaRepository, ensures all CRUD ops with Newsletter class.
 * @author milos jolovic
 */
@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter,Long> {
}
