package dsmt.model.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ORDER_DETAILS")
@IdClass(value = OrderDetail.class)
public class OrderDetail implements Serializable {
	
	private static final long serialVersionUID = -280655867447643142L;
	
	// @formatter:off
	
	@Id private Integer order_id;
	@Id private Integer product_id;
	@Column(name = "oldprice")
	private float oldPrice;
	private int quantity;
	
//	@JsonIgnore @ManyToOne @JoinColumn(name = "order_id")
//	private Order order;
	// @formatter:on
}
