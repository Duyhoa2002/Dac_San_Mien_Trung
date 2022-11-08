package dsmt.model.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CommentId implements Serializable {
	
	private static final long serialVersionUID = 1985192875367924598L;
	
	private String account_id;
	private Integer product_id;
}
