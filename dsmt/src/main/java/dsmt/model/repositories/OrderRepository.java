package dsmt.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dsmt.model.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
