package dsmt.model.services; 

import java.util.List;
import org.springframework.stereotype.Service;

import dsmt.model.entities.Account;
import dsmt.model.entities.Product;
import dsmt.model.repositories.ProductRepository;

@Service
public class ProductService extends AbstractService<Product, Integer>{

	@Override
	protected Integer getId(Product entity) {
		return entity.getId();
	}

	@Override
	public void remove(Integer id) throws IllegalArgumentException {
		ProductRepository rep2 = ((ProductRepository) super.rep);
		int i = rep2.deleteComments(id);
		if(i > 0) System.out.println("DELETE "+i+" COMMENTS BY PRODUCT'S ID = "+id);
		rep2.deleteId(id);
	}

	@Override
	public <S extends Product> S save(S entity) throws IllegalArgumentException {
		if(entity.getAccount()==null) {
			entity.setAccount(new Account(getUser("hoandps18107")));
		} return super.rep.save(entity);
	}
	
	@Override
	public <S extends Product> S update(S entity) throws IllegalArgumentException {
		return super.update(entity);
	}

	public List<Product> byAccountId(String username) {
		return ((ProductRepository) super.rep).findByAccountId(username);
	}

	public List<Product> disData(Integer top) {
		return ((ProductRepository) super.rep).disData(top);
	}

	public List<Product> topData(Integer top) {
		return ((ProductRepository) super.rep).topData(top);
	}

}
