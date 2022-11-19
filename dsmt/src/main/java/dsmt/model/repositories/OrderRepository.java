package dsmt.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dsmt.model.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query(value = "EXEC PROC_ORDER_ORDERS :status", nativeQuery = true)
	List<Order> findByStatus(Integer status);
	
	@Query(value = "SELECT * FROM ORDERS WHERE account_id=:id", nativeQuery = true)
	List<Order> findByAccountId(String id);

	@Query(value = "SELECT * FROM ORDERS WHERE product_id=:id", nativeQuery = true)
	List<Order> findByProductId(Integer id);
	
	
}
