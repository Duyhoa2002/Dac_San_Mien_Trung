package dsmt.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dsmt.model.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query(value = "EXEC PROC_DIS_PRODUCT :top", nativeQuery = true)
	List<Product> disData(Integer top);

	@Query(value = "EXEC PROC_TOP_PRODUCT :top", nativeQuery = true)
	List<Product> topData(Integer top);
	
}
