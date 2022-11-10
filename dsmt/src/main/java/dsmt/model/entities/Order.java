package dsmt.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder.ObtainVia;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ORDERS")
public class Order {
	
	// @formatter:off

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String address;
	@ObtainVia @Column(name = "regtime")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
	private Date regTime = new Date();
	
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	@OneToMany @JsonIgnoreProperties("order_id")
	private List<OrderDetail> order_details;
	
	@JoinColumn(name = "id", referencedColumnName = "order_id")
	@JsonIgnore @OneToOne private OrderStatus status;
	
	// @formatter:on

	
}
