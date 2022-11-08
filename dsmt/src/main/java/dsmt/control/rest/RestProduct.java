package dsmt.control.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dsmt.model.entities.Product;

@RestController
@CrossOrigin("*")
@RequestMapping({"/rest/products"})
public class RestProduct extends AbstractRESTful<Product, Integer>{
	
}
