package dsmt.control.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dsmt.model.entities.Comment;
import dsmt.model.entities.CommentId;

@RestController
@CrossOrigin("*")
@RequestMapping({"/rest/comments"})
public class RestComment extends AbstractRESTful<Comment, CommentId>{
	
}
