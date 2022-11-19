package dsmt.model.entities;

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
	
	public Account(String username) {
		this.username = username;
	}
}
