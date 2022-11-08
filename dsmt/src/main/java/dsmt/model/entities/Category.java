package dsmt.model.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dsmt.util.Random;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CATEGORIES")
public class Category {

	// @formatter:off
	
	@Id @Builder.ObtainVia @Column(length = 8)
	private String id = Random.UppAndLow("", 8);
	private String name;
	
	@JsonIgnore @OneToMany(mappedBy = "category")
	private List<Product> products;
	
	// @formatter:on

}
