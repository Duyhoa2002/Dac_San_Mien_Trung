package dsmt.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import dsmt.model.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Comment> {

	@Query(value = "SELECT * FROM COMMENTS WHERE account_id=:a", nativeQuery = true)
	public List<Comment> getListById(String a);

	@Query(value = "SELECT * FROM COMMENTS WHERE product_id=:p", nativeQuery = true)
	public List<Comment> getListById(Integer p);
	
	@Query(value = "SELECT * FROM COMMENTS WHERE account_id=:a AND product_id=:p", nativeQuery = true)
	public Comment getById(String a, Integer p);

	@Query(value = "DELETE FROM COMMENTS WHERE account_id=:a AND product_id=:p AND regTime=:t", nativeQuery = true)
	public int deleteById(String a, Integer p, String t);

	@Query(value = "DELETE FROM COMMENTS WHERE account_id=:a AND product_id=:p", nativeQuery = true)
	public int deleteById(String a, Integer p);
	
}