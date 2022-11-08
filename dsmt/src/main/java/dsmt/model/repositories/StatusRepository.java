package dsmt.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dsmt.model.entities.OrderStatus;

@Repository
public interface StatusRepository extends JpaRepository<OrderStatus, Integer> {

}
