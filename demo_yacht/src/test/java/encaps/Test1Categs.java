package encaps;

import java.io.IOException;
import java.util.List;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import encaps.demo.Categ;
import encaps.demo.CategRepo;
import encaps.demo.Item;
import encaps.demo.CategParser;
import encaps.utils.FilesUtils;

@SpringBootTest
class Test1Categs {

	@Autowired
	CategRepo repoCateg;

//	@Autowired
//	ItemService servItem;

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
		String url = "https://m.apolloduck.com/";
		Document doc = Jsoup.connect(url).get();
		List<Categ> listCat = CategParser.parseCategs(doc);
		listCat.forEach(cat -> {
			repoCateg.save(cat);
			try {
				Document doc2 = Jsoup.connect(cat.getHref()).get();
				FilesUtils.save(doc2.html());
				List<Categ> listCat2 = CategParser.parseCategs(doc2);
				listCat2.forEach(cat2 -> {
					cat2.setParentId(cat.getId());
					repoCateg.save(cat2);
				});
			} catch (IOException e) {
				e.printStackTrace();
			}

		});
	}

	// https://barges.apolloduck.com/listings.phtml
//	https://m.apolloduck.com/index.phtml
	private void getItems() throws Exception {
		Item item = new Item();
		// repoItem.save(item);

	}

}
