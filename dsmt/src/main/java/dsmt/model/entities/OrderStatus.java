package dsmt.model.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder.ObtainVia;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ORDER_STATUS")
public class OrderStatus {
	
	// @formatter:off

	@Id private Integer order_id;
	private String account_id;
	@ObtainVia private Integer status = 0;
	private String descript;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private Product product;
	
	// @formatter:on

}
