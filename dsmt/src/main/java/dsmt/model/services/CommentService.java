package dsmt.model.services; 

import org.springframework.stereotype.Service;

import dsmt.model.entities.Comment;
import dsmt.model.entities.CommentId;

@Service
public class CommentService extends AbstractService<Comment, CommentId>{

	@Override
	protected CommentId getId(Comment entity) {
		return entity.getId();
	}

}
