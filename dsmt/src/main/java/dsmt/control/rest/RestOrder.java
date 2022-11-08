package dsmt.control.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dsmt.model.entities.Order;

@RestController
@CrossOrigin("*")
@RequestMapping({"/rest/orders"})
public class RestOrder extends AbstractRESTful<Order, Integer>{
	
}
