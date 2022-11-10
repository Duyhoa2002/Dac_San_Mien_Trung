package dsmt.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder.ObtainVia;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderDetail.class)
@Entity(name = "ORDER_DETAILS")
public class OrderDetail implements Serializable {
	
	private static final long serialVersionUID = 505245298407682862L;
	
	// @formatter:off
	
	@Id private Integer order_id;
	@Id private Integer product_id;
	
	@ObtainVia @Column(name = "oldprice")
	private Float oldPrice = 0F;
	@ObtainVia
	private Integer quantity = 1;

	@JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
	@JsonIgnore @ManyToOne
	private Order order;
	
	@JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne @JsonIgnoreProperties({"id", "category", "account_id"})
	private Product product;
	
	// @formatter:on

}
