package dsmt.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dsmt.model.entities.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

}
