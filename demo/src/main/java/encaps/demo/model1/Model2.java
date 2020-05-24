package encaps.demo.model1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "model2")
public class Model2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@UpdateTimestamp
	private LocalDateTime updated;

	@ManyToOne
	@JoinColumn(name = "model1_id")
	private Model1 model1;
	
	@OneToMany(mappedBy="model2"
			, cascade = CascadeType.ALL
			, fetch = FetchType.EAGER
			)
	private List<Model3> model3s = new ArrayList<>();
	
	//////////////////////////////////////////////////////

	public Model2() {
		super();
	}

	public Model2(String title) {
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

	public Model1 getModel1() {
		return this.model1;
	}

	public void setModel1(Model1 model1) {
		this.model1 = model1;
	}
	
	public void addModel3(Model3 model3) {
		this.model3s.add(model3);
		if(model3.getModel2() != this)
			model3.setModel2(this);
	}		

	@Override
	public String toString() {
		String s = "Model2 [id=" + id + ", title=" + title + "]";
		if (model1 != null)
			s += "model1:" + model1.getId().toString();
		for(Model3 model3:model3s)
			s += String.format("\n\t\t%s", model3.toString());		
		return s;
	}

}
