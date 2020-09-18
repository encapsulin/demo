package helloWorld;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id = 0;

	@Column(unique = true)
	String title = "";

//	@LastModifiedDate
//	@Column(name = "updated")
	@Column(name="updated", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime updated;
//	Date updated;

	public ItemEntity() {

	}

	public ItemEntity(String title) {
		id = null;
		this.title = title;
		updated = LocalDateTime.now();
	}

	@Override
	public String toString() {
		String str = "\n ItemEntity:";
		str += "\n id=" + id;
		str += "\n title=" + title;
		str += "\n updated=" + updated;
		return str;

	}

}
