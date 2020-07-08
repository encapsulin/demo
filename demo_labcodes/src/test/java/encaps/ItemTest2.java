package encaps;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import encaps.demo.Item;
import encaps.demo.ItemParser;
import encaps.demo.ItemRepo;

@SpringBootTest
class ItemTest2 {

	@Autowired
	ItemRepo repoItem;

	WebDriver driver;
//	ECT * FROM `item` WHERE scol1 IS NOT NULL AND col5 >''
	@Test
	void contextLoads() throws Exception {
//		Pageable limit = PageRequest.of(0,10);
		Iterable<Item> listItems = repoItem.findAllByCol1NotNullAndScol1NullAndDoneNull();
		int limit = 0;
		for (Item item : listItems) {
			System.out.println(item);

			ItemParser.parseItemFile(item);
			try {
				repoItem.save(item);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.println(item);
//			if (limit >= 1)
//				break;
			System.out.println(limit++);

		}
//		System.out.println(listItems);
//		System.out.println(listItems.size());
	}
}