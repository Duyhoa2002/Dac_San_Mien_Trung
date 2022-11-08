package dsmt.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dsmt.model.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
