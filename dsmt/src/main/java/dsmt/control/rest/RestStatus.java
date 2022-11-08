package dsmt.control.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dsmt.model.entities.OrderStatus;

@RestController
@CrossOrigin("*")
@RequestMapping({"/rest/status"})
public class RestStatus extends AbstractRESTful<OrderStatus, Integer>{
	
}
