package dsmt.model.services; 

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dsmt.model.entities.Order;
import dsmt.model.entities.OrderDetail;
import dsmt.model.entities.OrderStatus;
import dsmt.model.repositories.OrderDetailRepository;
import dsmt.model.repositories.OrderStatusRepository;

@Service
public class StatusService extends AbstractService<OrderStatus, Integer>{

	@Autowired private OrderDetailRepository service;
	
	@Override
	protected Integer getId(OrderStatus entity) {
		return entity.getOrder_id();
	}

	public List<OrderStatus> byAccountId(String id) {
		return ((OrderStatusRepository) super.rep).findByAccountId(id);
	}
	
	public List<OrderStatus> byShipperId(String id) {
		return ((OrderStatusRepository) super.rep).findByShipperId(id);
	}

	public List<OrderStatus> byProductId(Integer id) {
		return ((OrderStatusRepository) super.rep).findByProductId(id);
	}
	
	@Override
	public <S extends OrderStatus> S update(S entity) throws IllegalArgumentException {
		Order order = entity.getOrder();
		if(order!=null) for(OrderDetail o : order.getOrder_details())
			service.updateQuantity(o);
		return super.update(entity);
	}

	public OrderStatus updateStatus(OrderStatus o, Integer status) {
		((OrderStatusRepository) super.rep).updateStatus(o, status);
		Optional<OrderStatus> optional = rep.findById(o.getOrder_id());
		if(optional.isPresent()) return optional.get();
		throw new IllegalArgumentException(o.getOrder_id()+" không tồn tại!");
	}
	
}
