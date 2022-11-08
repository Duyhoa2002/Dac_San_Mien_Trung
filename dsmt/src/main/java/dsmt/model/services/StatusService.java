package dsmt.model.services; 

import org.springframework.stereotype.Service;
import dsmt.model.entities.OrderStatus;

@Service
public class StatusService extends AbstractService<OrderStatus, Integer>{

	@Override
	protected Integer getId(OrderStatus entity) {
		return entity.getOrder_id();
	}

}
