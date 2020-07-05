package encaps;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import encaps.demo.Categ;
import encaps.demo.CategRepo;
import encaps.demo.Item;
import encaps.demo.ItemParser;
import encaps.demo.ItemRepo;

@SpringBootTest
class Test2Items {

	@Autowired
	CategRepo repoCateg;

	@Autowired
	ItemRepo repoItem;

	WebDriver driver;

	@Before(value = "")
	void testBefore() {
//		if (driver == null)
//			driver = new ChromeDriver();

	}

	@After(value = "")
	void testAfter() {
		if (driver != null) {
//			driver.close();
//			driver.quit();
		}
	}

	@Test
	void contextLoads() throws Exception {

		if (driver == null)
			driver = new ChromeDriver();

		Iterable<Categ> listCat = repoCateg.findByParentIdNotNull();
//		int lim = 7;
		for (Categ categ : listCat) {
//			if (lim++ <= 8)
//				continue;
			List<Item> list = parseItems(driver, categ.getHref());
			list.forEach(item -> {
				item.setCateg(categ);
				repoItem.save(item);
			});
//			if (--lim <= 0)
//				break;
		}

	}

	List<Item> parseItems(WebDriver driver, String url) {
		System.out.println("parseItems()" + url);
		driver.get(url);
		resolveCaptcha(driver, "div[id=dismiss-button");
		List<Item> parseItems = new ArrayList<>();

		do {
			parseItems.addAll(ItemParser.parseItems(driver.getPageSource()));
		} while (clickNext(driver));

		return parseItems;
	}

	boolean clickNext(WebDriver driver) {
		try {
			List<WebElement> elems = driver.findElements(By.cssSelector("div[class=paginate] a"));
			for(WebElement elem:elems) {
				if(elem.getText().contains("Next")) {
					elem.click();
					System.out.println("elem.click();");
					return true;
				}
			};			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	void resolveCaptcha(WebDriver driver, String pattern) {
		try {
			WebElement elem = driver.findElement(By.cssSelector(pattern));
			elem.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
