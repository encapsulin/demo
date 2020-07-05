package encaps.demo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ItemService {

	@Autowired
	ItemRepo repo;

	public Categ parseItemsAndSave(Categ categ) throws Exception {

		Document doc = Jsoup.connect(categ.getHref()).get();
//		Document doc = Jsoup.parse(new File("tmp/Friendship.html"), null);

//		categ.setLocation(ItemParser.parseCategLocation(doc));
//		categ.setDate2(ItemParser.parseCategDate2(doc));
//		categ.setTitle2(doc.title());
		List<Item> listItems = ItemParser.parseItems(doc.html());
		listItems.forEach(entity -> {
			entity.setCateg(categ);
			repo.save(entity);
		});
		categ.addItems(listItems);

		return categ;
	}
}
