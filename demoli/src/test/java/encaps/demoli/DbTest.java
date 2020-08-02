package encaps.demoli;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import encaps.demoli.db.Company;
import encaps.demoli.db.CompanyRepo;
import encaps.demoli.db.Vacancy;
import encaps.demoli.db.VacancyRepo;

@SpringBootTest
class DbTest {

	@Autowired
	CompanyRepo companyRepo;

	@Test
	void test() {		
		testCreate();
//		testDelete();
	}
	
	void testDelete() {
//		Optional<Company> company = new Company();
		//Optional<Company> company = companyRepo.findById(12L);
		companyRepo.deleteById(1);
	}
	
	void testCreate() {
		// fail("Not yet implemented");
		Company company = new Company();
		company.setTitle("asdf");

		Vacancy vacancy = new Vacancy();
		vacancy.setTitle("vactitle");
		vacancy.setCompany(company);

		company.addVacancy(vacancy);
		company = companyRepo.save(company);
		System.out.println(company.toString());
	}

}
