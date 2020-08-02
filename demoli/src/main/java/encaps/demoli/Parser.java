package encaps.demoli;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import encaps.demoli.db.Company;
import encaps.demoli.db.Employee;
import encaps.demoli.db.Vacancy;

public class Parser {

	public static final String WEB_ROOT = "https://www.linkedin.com";

	public static void main(String[] args) {
		test1();
	}

	public static List<Element> parseBlocks(String html) {
		Document doc = Jsoup.parse(html);
		Elements elems = doc.select("li");
		List<Element> elems2 = doc.select("li").stream().filter(li -> li.html().contains("/jobs/view"))
				.collect(Collectors.toList());
		return elems2;
	}

	public static String parseCompanyTitle(Element elem_) {
		Elements elems = elem_.select("a");
		Optional<Element> opt = elems.stream().filter(a -> a.attr("href").contains("/company/")).findFirst();
		if (opt.isPresent()) {
			return opt.get().text();
		}
		return "";
	}

	public static String parseCompanyUrl(Element elem_) {
		Elements elems = elem_.select("a");
		Optional<Element> opt = elems.stream().filter(a -> a.attr("href").contains("/company/")).findFirst();
		if (opt.isPresent()) {
			return WEB_ROOT + opt.get().attr("href");
		}
		return "";
	}

	public static String parseCompanyLocation(Element elem_) {
		Element elem = elem_.select("span").get(1);
		return elem.text();
	}

	public static String parseVacancyTitle(Element elem_) {
		Optional<Element> opt = elem_.select("a").stream().filter(a -> a.attr("href").contains("/jobs/view")).skip(1)
				.findFirst();
		if (opt.isPresent())
			return opt.get().text();
		return "";
	}

	static void test1() {
//		String fn = "log/1591045256886.html";
////		String fn = "log/employees.html";
//		String html = FilesUtils.read(fn);
//		List<Employee> listEmpls = Parser.parseEmployees(html);
//		System.out.println(listEmpls);
		String sUrl="https://www.linkedin.com/jobs/view/1876249772/?eBP=CwEAAAFycckEe_TJwJiZddsZFKxVP_pr8O6U37Nm6zVK96Ym0bzkzCfwl7T8D9CqdBN9TWTgJlQVPCljuR6lRW62Gbc1MaVJTNYggtY4OHcxokVlrh4qJM4Cx7RIcm9IB4M1olmqAy3l8MizShIsVYUVKiQD6sePbNqLvBaprSWqNMxIl7Bb7Z1jHl4Zd9yalfE6F2wKPWITkNNUEpJZvzXYbIpT78oX53g5QNGofgvAuidCk0ZScsNmYZv1Vh-0E40G05gFS4pGAkEMqDuXCFgafYrsvct7OsZ4D-Jnotemje8b3tKCfEOKGmmK8hTv89ISzn5r0ciAR6lgqloI5JGv_dGBC2yVS8yJGdEqFEiBDHRJKku69XtVtlgvL1adG_4YxO0wsk6qscNcAMpFzWvuySd284tdqCD_hUSXL2wo&recommendedFlavor=SKILL_ASSESSMENTS&refId=a4d350a2-9f38-43c8-96c0-cc6a59fea05c&trk=d_flagship3_search_srp_jobs";
		System.out.println(sUrl.substring(0, sUrl.indexOf("?")));
			
	}
	
	public static String parseVacancyUrl(Element elem_) {
		Optional<Element> opt = elem_.select("a").stream().filter(a -> a.attr("href").contains("/jobs/view"))
				.findFirst();
		if (opt.isPresent()) {
			String sUrl = WEB_ROOT + opt.get().attr("href");
			return sUrl.substring(0, sUrl.indexOf("?"));
		}
		return "";
	}

	public static String parseEmployeesLink(String html) {
//		data-control-name="topcard_see_all_employees"
		Document doc = Jsoup.parse(html);
		Optional<Element> opt = doc.select("a").stream()
				.filter(a -> a.attr("data-control-name").equals("topcard_see_all_employees")).findFirst();
		if (opt.isPresent())
			return WEB_ROOT + opt.get().attr("href");
		return "";
	}

	public static List<Employee> parseEmployees(String html) {
		Document doc = Jsoup.parse(html);

		List<Employee> listEmpls = new ArrayList<>();

		Elements elemsEmployee = doc.select("div.search-result__wrapper");
		for (Element elemEmployee : elemsEmployee) {
			// System.out.println(elemEmployee.html());
			Employee employee = new Employee();
			employee.setName(elemEmployee.select("button").attr("aria-label"));
			employee.setUrl(WEB_ROOT + elemEmployee.select("a").stream().filter(a -> a.attr("href").startsWith("/in/"))
					.map(a -> a.attr("href")).findFirst().get());
			listEmpls.add(employee);
		}

		return listEmpls;
	}

	public static List<Vacancy> getVacanciesList(String html) {

		Document doc = Jsoup.parse(html);
		List<Element> elems = Parser.parseBlocks(html);

		List<Vacancy> vacancies = new ArrayList();

		for (Element elem : elems) {
			// System.out.println(elem.html());

			Company company = new Company();
			company.setTitle(Parser.parseCompanyTitle(elem));
			company.setUrl(Parser.parseCompanyUrl(elem));
			company.setLocation(Parser.parseCompanyLocation(elem));

			Vacancy vacancy = new Vacancy();
			vacancy.setTitle(Parser.parseVacancyTitle(elem));
			vacancy.setUrl(Parser.parseVacancyUrl(elem));
			//vacancy.setCompany(company);

			// System.out.println(vacancy);

			vacancies.add(vacancy);
		}
		return vacancies;
	}

	public static boolean skipList(String str_) {
		List<String> skipList = new ArrayList<>();
		skipList.add("jobs/view/1876249772");
		for (String strSkip : skipList)
			if (str_.contains(strSkip))
				return true;
		return false;
	}

}
