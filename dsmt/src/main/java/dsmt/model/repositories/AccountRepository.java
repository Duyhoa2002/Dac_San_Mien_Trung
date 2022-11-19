package dsmt.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dsmt.model.entities.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	@Query(value = "EXEC PROC_ACCOUNT_BY_ROLE :role_id", nativeQuery = true)
	List<Account> findByRole(String role_id);

}
