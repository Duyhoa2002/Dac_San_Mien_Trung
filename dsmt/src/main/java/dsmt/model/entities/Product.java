package dsmt.model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PRODUCTS")
public class Product {
	 
	// @formatter:off
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;
	@Column(name = "cosprice") private float cosPrice;
	@Column(name = "proprice") private float proPrice;
	private String name;
	private String descript;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	private Category category;
	private String account_id;
	
	@ElementCollection @Column(name = "image")
	@CollectionTable (
		name = "PRODUCT_IMAGES",
		joinColumns = @JoinColumn(name = "product_id")
	) private List<String> images;
	
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	@JsonIgnore @OneToMany List<OrderDetail> order_details;
	
	// @formatter:on

	
}
