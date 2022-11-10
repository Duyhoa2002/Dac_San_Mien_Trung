package dsmt.control.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dsmt.model.entities.Comment;
import dsmt.model.entities.CommentId;

@RestController
@CrossOrigin("*")
@RequestMapping({"/rest/comments"})
public class RestComment extends AbstractRESTful<Comment, CommentId>{

	@GetMapping({"","/**"}) // reading method to get data
	public ResponseEntity<Object> get(
			@RequestParam(required = false) String account_id,
			@RequestParam(required = false) Integer product_id
	){
		return super.getData(new CommentId(account_id, product_id));
	}
	
	@DeleteMapping({"","/**"}) // Delete method to remove entity
	public ResponseEntity<Void> delete(
			@RequestParam(required = false) String account_id,
			@RequestParam(required = false) Integer product_id
	) throws IllegalArgumentException {
		if(account_id != null && product_id != null) {
			dao.remove(new CommentId(account_id, product_id));
			return ResponseEntity.ok().build();
		} else return ResponseEntity.noContent().build();
	}
	
}
