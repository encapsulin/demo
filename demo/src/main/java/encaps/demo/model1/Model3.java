package encaps.demo.model1;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "model3")
public class Model3 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@UpdateTimestamp
	private LocalDateTime updated;

	@ManyToOne
	@JoinColumn(name = "model2_id")
	private Model2 model2;

	public Model3() {
		super();
	}

	public Model3(String title) {
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

	public Model2 getModel2() {
		return this.model2;
	}

	public void setModel2(Model2 model2) {
		this.model2 = model2;
	}
	
	@Override
	public String toString() {
		String s = "Model3 [id=" + id + ", title=" + title + "]";
		if (model2 != null)
			s += "model2:" + model2.getId().toString();
		return s;
	}

}
