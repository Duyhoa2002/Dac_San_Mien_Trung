package dsmt.model.services; 

import org.springframework.stereotype.Service;
import dsmt.model.entities.Order;
@Service
public class OrderService extends AbstractService<Order, Integer>{

	@Override
	protected Integer getId(Order entity) {
		return entity.getId();
	}

}
