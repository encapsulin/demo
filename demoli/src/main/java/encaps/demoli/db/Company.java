package encaps.demoli.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String title;
	String url;
	String location;
	Integer employeesTotal;
	Integer employeesJava;
	String urlEmployees;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	List<Vacancy> vacancies;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	List<Employee> employees;

	public Company() {
		// listEmployees = new ArrayList<>();
		vacancies = new ArrayList<>();
	}



	@Override
	public String toString() {
		return "Company [id=" + id + ", title=" + title + ", url=" + url + ", location=" + location
				+ ", employeesTotal=" + employeesTotal + ", employeesJava=" + employeesJava + ", urlEmployees="
				+ urlEmployees + ", vacancies=" + vacancies + "]";
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getEmployeesTotal() {
		return employeesTotal;
	}

	public void setEmployeesTotal(Integer employeesTotal) {
		this.employeesTotal = employeesTotal;
	}

	public Integer getEmployeesJava() {
		return employeesJava;
	}

	public void setEmployeesJava(Integer employeesJava) {
		this.employeesJava = employeesJava;
	}

	public String getUrlEmployees() {
		return urlEmployees;
	}

	public void setUrlEmployees(String urlEmployees) {
		this.urlEmployees = urlEmployees;
	}

	public List<Vacancy> getVacancies() {
		return vacancies;
	}

	public void setVacancies(List<Vacancy> vacancies) {
		this.vacancies = vacancies;
	}

	public void addVacancy(Vacancy vacancy) {
		this.vacancies.add(vacancy);
	}

//	public List<Employee> getListEmployees() {
//		return listEmployees;
//	}
//
//	public void setListEmployees(List<Employee> listEmployees) {
//		this.listEmployees = listEmployees;
//	}

}
