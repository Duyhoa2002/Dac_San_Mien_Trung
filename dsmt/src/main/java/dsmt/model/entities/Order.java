package dsmt.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;
	private String address;
	@ObtainVia @Column(name = "regtime")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
	private Date regTime = new Date();
	
//	@OneToMany @JoinColumn(name = "order_id")
//	private List<OrderDetail> details;
	// @formatter:on

	
}