package dsmt.control.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dsmt.model.entities.Account;
import dsmt.model.services.AccountService;

@RestController
@CrossOrigin("*")
@RequestMapping({"/rest/accounts"})
public class RestAccount extends AbstractRESTful<Account, String>{
	
	@GetMapping("/role")
	public ResponseEntity<Object> getByRole(@RequestParam(required = false) String r) {
		return ResponseEntity.ok(((AccountService) dao).getByRole(r));
	}
}
