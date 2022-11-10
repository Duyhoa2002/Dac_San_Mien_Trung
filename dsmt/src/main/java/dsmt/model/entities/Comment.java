package dsmt.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder.ObtainVia;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "COMMENTS")
public class Comment {
	
	@EmbeddedId private CommentId id;
	private String descript;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
	@ObtainVia @Column(name = "regtime")
	private Date regTime = new Date();
	
}
