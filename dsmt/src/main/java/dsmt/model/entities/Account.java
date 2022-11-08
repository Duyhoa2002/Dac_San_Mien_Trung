package dsmt.model.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ACCOUNTS")
public class Account {
	
	@Id private String username;
	private String password;
	private String email;
	private String name;
	
	@ElementCollection @Column(name = "role_id") // phan quyen he thong
	@CollectionTable(name = "AUTHORITIES", joinColumns = { @JoinColumn(name = "account_id") })
	private Set<String> roles;
	
	// FOR SELLER
	@ElementCollection @Column(name = "id") // san pham dang ban
	@CollectionTable(name = "PRODUCTS", joinColumns = { @JoinColumn(name = "account_id") })
	private Set<Integer> products;
	
	// FOR BUYER
	@ElementCollection @Column(name = "id") // san pham da mua
	@CollectionTable(name = "ORDERS", joinColumns = { @JoinColumn(name = "account_id") })
	private Set<Integer> orders;

	// FOR SHIPPER
	@ElementCollection @Column(name = "order_id") // danh gia san pham
	@CollectionTable(name = "ORDER_STATUS", joinColumns = { @JoinColumn(name = "account_id") })
	private List<Integer> status;

	// FOR ANY ROLES
	@ElementCollection @Column(name = "product_id") // danh gia san pham
	@CollectionTable(name = "COMMENTS", joinColumns = { @JoinColumn(name = "account_id") })
	private List<Integer> comments;
}
