package encaps.demoli.db;

import javax.persistence.*;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	String name;
	String url;
	String position;
	String location;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	Company company;

	@Override
	public String toString() {
		String sPosition = ",\n\t\t\t sPosition=" + this.position;
		if (sPosition.toLowerCase().contains("java"))
			sPosition = ",\n>>>>>>>>>>>>>> sPosition=" + this.position;

		return "\n\t\t Employee:\n\t\t id=" + id + ",\n\t\t\t sName=" + name + ",\n\t\t\t sUrl=" + url + sPosition
				+ ",\n\t\t\t sLocation=" + location;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String sName) {
		this.name = sName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String sUrl) {
		this.url = sUrl;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String sPosition) {
		this.position = sPosition;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String sLocation) {
		this.location = sLocation;
	}

}
