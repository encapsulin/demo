package encaps.demo.model1;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Model4 {
	
	@Id
	Integer id=0;
	
	String title="";

	public Model4(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Model4 [title=" + title + "]";
	}
	
	

}
