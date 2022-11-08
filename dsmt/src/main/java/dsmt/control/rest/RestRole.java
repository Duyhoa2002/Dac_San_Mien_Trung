package dsmt.control.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dsmt.model.entities.Role;

@RestController
@CrossOrigin("*")
@RequestMapping({"/rest/roles"})
public class RestRole extends AbstractRESTful<Role, String>{
	
}
