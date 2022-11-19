package dsmt.model.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import dsmt.model.entities.OrderStatus;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer>{

	@Query(value = "EXEC PROC_ORDER_STATUS_A :id", nativeQuery = true)
	List<OrderStatus> findByAccountId(String id);

	@Query(value = "EXEC PROC_ORDER_STATUS_P :id", nativeQuery = true)
	List<OrderStatus> findByProductId(Integer id);

	@Modifying @Transactional // update status
	@Query(value = "UPDATE ORDER_STATUS SET status=:status, descript=:#{#e.descript} "
			+ "WHERE order_id=:#{#e.order_id}", nativeQuery = true)
	int updateStatus(OrderStatus e, Integer status);
}
