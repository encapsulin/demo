package encaps;

import java.io.File;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import encaps.amaterbox.Categ;
import encaps.amaterbox.CategRepo;
import encaps.amaterbox.Item;
import encaps.amaterbox.ParserItem;

@SpringBootTest
class DemoAmaterboxApplicationTests {

	@Test
	void contextLoads() throws Exception {
//		 get_categs();
		getItems();
	}

	@Autowired
	CategRepo repo;

	private void getItems() throws Exception {
		Iterable<Categ> list = repo.findAll();
		int i = 0;
		for (Categ categ : list) {
			if (i++ > 3)
				break;
			 String url = categ.getHref();
			 Document doc = Jsoup.connect(url).get();
			 
			 categ.setLocation(ParserItem.parseCategLocation(doc));
			 categ.setDate2(ParserItem.parseCategDate2(doc));
			 categ.setTitle2(doc.title());
			 List<Item> listItems = ParserItem.parseItems(doc.html());
			 
			 
		}
	}

	private void get_categs() throws Exception {


		String html = "tmp/AmateurBoxingResults.html";
		File f = new File(html);
		Document doc = Jsoup.parse(f, "UTF-8");

		List<Element> listCategs = doc.select("p[align=left]");
		int i = 0;
		for (Element elem : listCategs) {
			if (elem.html().contains(".pdf"))
				continue;

			System.out.println(elem.html());

			Categ categ = ParserItem.parseCateg(elem.html());

			if (!categ.getHref().endsWith(".html"))
				continue;

			System.out.println(categ);
			repo.save(categ);
			i++;
		}

		System.out.println(i);

	}

}
