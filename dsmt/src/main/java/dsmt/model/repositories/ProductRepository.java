package dsmt.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dsmt.model.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
