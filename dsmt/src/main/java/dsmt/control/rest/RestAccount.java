package dsmt.control.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dsmt.model.entities.Account;

@RestController
@CrossOrigin("*")
@RequestMapping({"/rest/accounts"})
public class RestAccount extends AbstractRESTful<Account, String>{
	
}
