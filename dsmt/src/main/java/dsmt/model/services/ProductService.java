package dsmt.model.services; 

import org.springframework.stereotype.Service;

import dsmt.model.entities.Product;

@Service
public class ProductService extends AbstractService<Product, Integer>{

	@Override
	protected Integer getId(Product entity) {
		return entity.getId();
	}

}
