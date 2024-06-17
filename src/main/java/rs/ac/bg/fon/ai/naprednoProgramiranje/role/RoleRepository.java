package rs.ac.bg.fon.ai.naprednoProgramiranje.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Role repo that extends JpaRepository which ensures all CRUD ops on Role class.
 * @author milos jolovic
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
