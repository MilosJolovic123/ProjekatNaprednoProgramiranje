package rs.ac.bg.fon.ai.naprednoProgramiranje.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repo interface that extends JpaRepository and ensures all CRUD ops on Review class in H2.
 * @author milos jolovic
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
