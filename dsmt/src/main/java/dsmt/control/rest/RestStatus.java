package dsmt.control.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dsmt.model.entities.OrderStatus;
import dsmt.model.services.StatusService;

@RestController
@CrossOrigin("*")
@RequestMapping({"/rest/status"})
public class RestStatus extends AbstractRESTful<OrderStatus, Integer>{
	
	@GetMapping("/account")
	public ResponseEntity<Object> getByAccount(@RequestParam(required = false) String id) {
		id = super.getUser(id==null?"hoandps18107":id);
		return ResponseEntity.ok(((StatusService) super.dao).byAccountId(id));
	}
	
	@GetMapping("/shipper")
	public ResponseEntity<Object> getByShipper(@RequestParam(required = false) String id) {
		id = super.getUser(id==null?"shipper1":id);
		return ResponseEntity.ok(((StatusService) super.dao).byShipperId(id));
	}
	
	@GetMapping("/product")
	public ResponseEntity<Object> getByAccount(@RequestParam(required = false) Integer id) {
		return ResponseEntity.ok(((StatusService) super.dao).byProductId(id));
	}

	@PutMapping("/progress")
	public ResponseEntity<Object> upStatus(
			@RequestBody(required = false) OrderStatus o,
			@RequestParam(required = false) Integer s
	) {
		return ResponseEntity.ok(((StatusService) super.dao).updateStatus(o,s));
	}
}
