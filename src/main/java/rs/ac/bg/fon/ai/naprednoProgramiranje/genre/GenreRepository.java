package rs.ac.bg.fon.ai.naprednoProgramiranje.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface that extends JpaRepository and ensures CRUD ops with Genre class.
 * @author milos jolovic
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {
}
