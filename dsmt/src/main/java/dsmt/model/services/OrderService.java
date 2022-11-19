package dsmt.model.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dsmt.model.entities.Order;
import dsmt.model.entities.OrderDetail;
import dsmt.model.repositories.OrderDetailRepository;
import dsmt.model.repositories.OrderRepository;

@Service
public class OrderService extends AbstractService<Order, Integer> {

	@Autowired
	private OrderDetailRepository service;

	@Override
	protected Integer getId(Order entity) {
		return entity.getId();
	}
	
	public List<Order> byStatus(Integer status) {
		return ((OrderRepository) super.rep).findByStatus(status);
	}

	public List<Order> byAccountId(String id) {
		return ((OrderRepository) super.rep).findByAccountId(id);
	}

	public List<Order> byProductId(Integer id) {
		return ((OrderRepository) super.rep).findByProductId(id);
	}

	@Override
	public <S extends Order> S save(S e) throws IllegalArgumentException {
		e.setRegTime(new Date());
		e.setAccount_id("buyer1");
		Integer id = rep.save(new Order(e.getAddress(), e.getRegTime(), e.getAccount_id())).getId();
		e.setId(id);
		e.getOrder_details().forEach(x -> x.setOrder_id(id));
		return bothSave(e);
	}

	@Override
	public <S extends Order> S update(S entity) throws IllegalArgumentException {
		return this.bothSave(entity);
	}
	
	private <S extends Order> S bothSave(S e) throws IllegalArgumentException {
		// update children
		service.deleteByOrderId(e.getId());
		List<OrderDetail> ods = e.getOrder_details();
		ods.forEach(x -> service.saveOrigin(x));

		// update
		e.setOrder_details(new ArrayList<>());
		return super.update(e);
	}

}
