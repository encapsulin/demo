package encaps.demo.model1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="model1")
public class Model1 {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@UpdateTimestamp
	private LocalDateTime updated; 	
	
	@OneToMany(mappedBy="model1"
			, cascade = CascadeType.ALL
			, fetch = FetchType.EAGER
			)
	private List<Model2> model2s = new ArrayList<>();
	
	public Model1() {
		super();	
	}	
	
	public Model1(String title) {
		super();
		this.title = title;
	}		

	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void addModel2(Model2 model2) {
		this.model2s.add(model2);
		if(model2.getModel1() != this)
			model2.setModel1(this);
	}

	@Override
	public String toString() {
		String s = String.format("\nModel1 [id=%d, title=%s, date=%s]",id, title, updated);			
			for(Model2 model2:model2s)
				s += String.format("\n\t%s", model2.toString());
		return s;
	}

}
