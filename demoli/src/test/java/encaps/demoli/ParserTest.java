package encaps.demoli;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import encaps.demoli.db.Employee;

class ParserTest {

	@Test
	void test() {
//		fail("Not yet implemented");
		test2();
	}
	
	void test2() {		
		String html = FilesUtils.read("log/employees.html");
		List<Employee> listEmpls = Parser.parseEmployees(html);
		System.out.println(listEmpls);
	}
	
	void test1() {

		String html = FilesUtils.read("log/2company.html");
		String href = Parser.parseEmployeesLink(html);
		System.out.println(href);
	}	
	

}
