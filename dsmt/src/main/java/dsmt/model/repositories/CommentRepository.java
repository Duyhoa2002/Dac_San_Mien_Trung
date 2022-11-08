package dsmt.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dsmt.model.entities.Comment;
import dsmt.model.entities.CommentId;
@Repository
public interface CommentRepository extends JpaRepository<Comment, CommentId> {

}
