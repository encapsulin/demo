package encaps;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import encaps.amaterbox.Categ;
import encaps.amaterbox.CategRepo;

@SpringBootTest
class DemoAmaterboxApplicationTests {

	@Test
	void contextLoads() throws Exception {
		get_categs();
	}

	@Autowired
	CategRepo repo;

	private void get_categs() throws Exception {
		String url = "http://amateur-boxing.strefa.pl/";
		Document doc = Jsoup.connect(url).get();
		
		List<Element> listCategs = doc.select(evaluator)
		Categ categ = new Categ();
		categ.setTitle("title");
		repo.save(categ);

	}

}
