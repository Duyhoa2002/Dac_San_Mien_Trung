package dsmt.model.services; 

import java.util.List;
import org.springframework.stereotype.Service;
import dsmt.model.entities.Product;
import dsmt.model.repositories.ProductRepository;

@Service
public class ProductService extends AbstractService<Product, Integer>{

	@Override
	protected Integer getId(Product entity) {
		return entity.getId();
	}
	
	public List<Product> disData(Integer top) {
		return ((ProductRepository) super.rep).disData(top);
	}

	public List<Product> topData(Integer top) {
		return ((ProductRepository) super.rep).topData(top);
	}

}
