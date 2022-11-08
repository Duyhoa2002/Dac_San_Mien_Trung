package dsmt.model.services; 

import org.springframework.stereotype.Service;

import dsmt.model.entities.Account;
@Service
public class AccountService extends AbstractService<Account, String>{

	@Override
	protected String getId(Account entity) {
		return entity.getUsername();
	}

}
