package encaps.demoli;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import encaps.demoli.db.Company;
import encaps.demoli.db.CompanyRepo;
import encaps.demoli.db.Vacancy;

@SpringBootTest
class DemoliApplicationTests {

	public static final String WEB_ROOT = "https://www.linkedin.com";

//	@Test
//	void contextLoads() {
//	}

	@Autowired 
	CompanyRepo companyRepo;
	
	@Test
	void asdf() {
		Company company = new Company();
		company.setTitle("asdf");
		System.out.println(company.toString());
		
		company = companyRepo.save(company);
		System.out.println(company.toString());		
		
		Vacancy vacancy = new Vacancy();
		vacancy.setTitle("vactitle");
		
		company.addVacancy(vacancy);
		company = companyRepo.save(company);
		System.out.println(company.toString());
	}
	
	@Test
	void parse() {
		System.out.println("-= BEGIN =-");

		try {
			String html = "";
			html = getHtml("https://www.linkedin.com/jobs/search/?geoId=101282230&keywords=java&location=Germany");
			// html = FilesUtils.read("log/1591017155818.html");

			List<Vacancy> listVacancies = Parser.getVacanciesList(html);
			// listVacancies.stream().limit(3).forEach(System.out::println);
			int limit = 1;
			for (Vacancy vacancy : listVacancies) {

//				if (Parser.skipList(vacancy.sUrl))
//					continue;

				if (limit-- == 0)
					break;

//				html = getHtml(vacancy.company.sUrl);
////				// html = FilesUtils.read("log/2company.html");
//				vacancy.company.sUrlEmployees = Parser.parseEmployeesLink(html);
//				html = getHtml(vacancy.company.sUrlEmployees);
//				
//				List<Employee> listEmpls = Parser.parseEmployees(html);
//				vacancy.company.listEmployees.addAll(listEmpls);
//							
//				//vacancy.company.listEmployees.stream().filter(emp->emp.sPosition.toLowerCase().contains("java"))
//				if(vacancy.sTitle.toLowerCase().contains("java")  )
//					System.out.println("vvvvvvvvvvvvvvvvvvvvv");
//				System.out.println(vacancy);		

				Thread.sleep(10000);
				pagination();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
//		//driver.close();
//		//driver.quit();
		System.out.println("-= END =-");
	}

	void pagination() {
		List<WebElement> list = driver.findElements(By.cssSelector("ul"));
		System.out.println("list:" + list);
		//String html = driver.getPageSource();
		//FilesUtils.save(html);
		// System.out.println(html);
		list.stream().forEach(x->System.out.println(x.getAttribute("innerHTML")));
		List<WebElement> listFiltered = list.stream()
				.filter(x->x.getAttribute("innerHTML").matches(".*button.*Page.*"))
				.collect(Collectors.toList());
	}

	WebDriver driver;

	public String getHtml(String sUrl) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "/opt/geckodriver");
		// WebDriver driver = new FirefoxDriver();
		if (driver == null)
			driver = new FirefoxDriver();

		driver.get(sUrl);
		TimeUnit.SECONDS.sleep(1);

		String html = null;

		html = driver.getPageSource();
		FilesUtils.save(html);

		WebElement elem = null;
		try {
			List<WebElement> elems = driver.findElements(By.tagName("a"));
			// elems.stream().peek(a-> System.out.println(a.getText())).count();

			Optional<WebElement> opt = elems.stream().filter(a -> a.getText().equals("Sign in")).findFirst();
			if (opt.isPresent()) {
				elem = opt.get();
				elem.click();
				TimeUnit.SECONDS.sleep(1);

				int i = 60;
				elem = null;
				while (i-- > 0 && elem == null) {
					try {
						elem = driver.findElement(By.id("username"));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					TimeUnit.SECONDS.sleep(1);
					System.out.println(i);
				}

				elem = driver.findElement(By.id("username"));
				elem.sendKeys("encapsulin@gmail.com");
				elem = driver.findElement(By.id("password"));
				elem.sendKeys("fJ)a4dMxl" + Keys.ENTER);
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		int i = 10;
//		 elem = null;
//		 String sElem = null;
//		while (i-- > 0 && elem == null) {
//			try {
//				//elem = driver.findElement(By.id("ember24"));
//				 sElem = driver.getPageSource();
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//			TimeUnit.SECONDS.sleep(1);
//			System.out.println(i);
//		}

		html = "";
		while (i-- > 0 && !html.contains("id=\"ember24")) {
			try {
				html = driver.getPageSource();
				TimeUnit.SECONDS.sleep(3);
				System.out.println(i);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

		TimeUnit.SECONDS.sleep(10);
		html = driver.getPageSource();
		FilesUtils.save(html);

		return html;
	}

}
