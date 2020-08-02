package encaps.demoli.db;

import javax.persistence.*;

@Entity
public class Vacancy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	String title;
	
	String url;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	Company company;

	public Vacancy() {
		
	}

	@Override
	public String toString() {
		return "Vacancy [id=" + id + ", title=" + title + ", url=" + url + ", company=" + company + "]";
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
