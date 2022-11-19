package dsmt.model.services; 

import java.util.List;

import org.springframework.stereotype.Service;

import dsmt.model.entities.Account;
import dsmt.model.repositories.AccountRepository;
@Service
public class AccountService extends AbstractService<Account, String>{

	@Override
	protected String getId(Account entity) {
		return entity.getUsername();
	}
	
	public List<Account> getByRole(String role_id) {
		return ((AccountRepository) super.rep).findByRole(role_id);
	}

}
