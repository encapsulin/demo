package encaps.amaterbox;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "categ",
indexes = {@Index(name = "index_title",  columnList="title", unique = true),
           @Index(name = "index_href", columnList="href", unique = true)})

public class Categ {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String title;
	String href;
	
	@OneToMany(mappedBy="categ", cascade = CascadeType.ALL)
	List<Item> items;
	
//	@Override
//	public String toString() {
//		return "\nState [id=" + id + ", title=" + title + ", href=" + href + "]";
//	}
	
	
}
