package dsmt.control.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dsmt.model.entities.Category;

@RestController
@CrossOrigin("*")
@RequestMapping({"/rest/categories"})
public class RestCategory extends AbstractRESTful<Category, String>{
	
}
