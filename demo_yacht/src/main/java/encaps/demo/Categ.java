package encaps.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "categ", indexes = {
//		@Index(name = "index_title",  columnList="title", unique = true),
//           @Index(name = "index_href", columnList="href", unique = true)
           }
)

public class Categ {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String href;
	String title;
	Integer parentId;
			
	@OneToMany(mappedBy="categ", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Item> items;
	
	public Categ() {
		items = new ArrayList<>();
	}

	public void addItems(List<Item> items) {
		this.items.addAll(items);
	}

	@Override
	public String toString() {
		return "\nCateg [id=" + id 
				+ "\n, href=" + href 
				+ "\n, title=" + title 
				+ "\n, items.size=" + items.size() 
				+ "]";
	}
	
	
}
