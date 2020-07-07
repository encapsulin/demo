package encaps;

import java.io.IOException;
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

import encaps.demo.Item;
import encaps.demo.ItemParser;
import encaps.demo.ItemRepo;
import encaps.utils.FilesUtils;

@SpringBootTest
class ItemTest {

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

		List<Item> listItems = parseItems();
		System.out.println(listItems);
		System.out.println(listItems.size());

	}

	List<Item> parseItems() throws Exception {

		if (driver == null)
			driver = new ChromeDriver();

		String url = "https://bepalingen.nhg.org/labcodes/determinations";
		System.out.println("parseItems()" + url);
		driver.get(url);

		List<Item> listItems = new ArrayList<>();

		int iPage = 1;
		do {
			String html = driver.getPageSource();
//			Thread.sleep(3000);
//			FilesUtils.save(html);
			List<Item> listItems_ = ItemParser.parseItems(html);
			listItems.addAll(listItems_);

			System.out.println(listItems_);
			System.out.println(listItems_.size());

			parseItemz();

		} while (clickNext(iPage++));

		return listItems;
	}

	void parseItemz() throws Exception {
		System.out.println("parseItemz()");
		List<WebElement> list = driver.findElements(By.cssSelector("div.table-container table tbody tr"));
		for (WebElement tr : list) {
			tr.click();
			
			Item item = ItemParser.parseItem(tr.getAttribute("outerHTML"));
			String sBash = String.format("sleep 2; xdotool mousemove 500 500 click 3\n" + 
					"sleep 2; xdotool mousemove_relative 10 100 click 1\n" + 
					"sleep 2; xdotool type \"page_1_item_%s\"\n" + 
					"sleep 2; xdotool key Return\n" + 
					"");
			Runtime.getRuntime().exec(sBash);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		;
	}

	boolean clickNext(int iPage_) {
		if (iPage_ > 1)
			return false;

		String url = "https://bepalingen.nhg.org/labcodes/determinations?page=" + iPage_;
		driver.get(url);
		return true;
	}

}
