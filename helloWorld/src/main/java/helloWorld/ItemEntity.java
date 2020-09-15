package helloWorld;

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
	Integer id=1;

//	@Column(unique = true)
	String title="";

	public ItemEntity() {

	}
	
	public ItemEntity(int id, String string) {
		this.id = id;
		this.title = title;
	}

	@Override
	public String toString() {
		return "\nItemEntity [id=" + id + ", title=" + title + "]";
	}

}
