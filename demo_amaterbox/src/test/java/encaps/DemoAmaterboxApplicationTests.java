package encaps;

import java.io.File;
import java.util.List;
import java.util.function.Supplier;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import encaps.amaterbox.Categ;
import encaps.amaterbox.CategRepo;
import encaps.amaterbox.Item;
import encaps.amaterbox.ItemParser;
import encaps.amaterbox.ItemService;

@SpringBootTest
class DemoAmaterboxApplicationTests {

	@Test
	void contextLoads() throws Exception {
		get_categs();
		getItems();
	}

	@Autowired
	CategRepo repoCateg;

	@Autowired
	ItemService servItem;

	private void getItems() throws Exception {
//		
		int i = 0;
//		Categ categ = repoCateg.findById(1).orElseThrow(() -> new Exception("Categ(1) not found"));
		Iterable<Categ> list = repoCateg.findAll();
		for (Categ categ : list) 
		{
//			if (i++ > 3)
//				break;

			categ = servItem.parseItemsAndSave(categ);
			System.out.println(categ);
			repoCateg.save(categ);
		}
	}

	private void get_categs() throws Exception {

		String html = "tmp/AmateurBoxingResults.html";
		Document doc = Jsoup.parse(new File(html), "UTF-8");

		List<Element> listCategs = doc.select("p[align=left]");
		int i = 0;
		for (Element elem : listCategs) {
			if (elem.html().contains(".pdf"))
				continue;

			System.out.println(elem.html());

			Categ categ = ItemParser.parseCateg(elem.html());

			if (!categ.getHref().endsWith(".html"))
				continue;

			System.out.println(categ);
			repoCateg.save(categ);

//			if (++i >= 3)
//				break;
		}

		System.out.println(i);

	}

}
