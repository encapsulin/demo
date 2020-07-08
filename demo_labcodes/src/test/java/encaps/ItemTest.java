package encaps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
			resolveCaptcha();
			String html = driver.getPageSource();

			List<Item> listItems_ = ItemParser.parseItems(html);
			int iElem = 1;
			for (Item item : listItems_) {
				try {
					item.setPage(iPage);
					repoItem.save(item);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				// scrapeItemz(iPage, iElem++, item.getCol1());
//				if (iElem >= 3)
//					break;
			}
			listItems.addAll(listItems_);

			System.out.println(listItems_);
			System.out.println(listItems_.size());

		} while (clickNext(++iPage));

		return listItems;
	}

	private void resolveCaptcha() {
		try {
			driver.findElement(By.cssSelector("a[class=cookie-notice__accept]")).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	void scrapeItemz(int iPage, int iElem_, String s_) throws Exception {
		System.out.println("scrapeItemz()");
		try {
			WebElement tr = driver.findElements(By.cssSelector("div.table-container table tbody tr")).get(iElem_ - 1);

			tr.click();
			Thread.sleep(3000);
			Runtime.getRuntime().exec("xdotool key Escape");
			Thread.sleep(1000);
			Runtime.getRuntime().exec("xdotool mousemove 500 500");
			Thread.sleep(1000);
			Runtime.getRuntime().exec("xdotool click 3");
			Thread.sleep(1000);
			Runtime.getRuntime().exec("xdotool mousemove_relative 10 100");
			Thread.sleep(1000);
			Runtime.getRuntime().exec("xdotool click 1");
			Thread.sleep(1000);
			Runtime.getRuntime().exec("xdotool type page_" + iPage + "_item_" + s_);
			Thread.sleep(1000);
			Runtime.getRuntime().exec("xdotool key Return");
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	boolean clickNext(int iPage_) {
		System.out.println("clickNext()" + iPage_);
//		if (iPage_ >= 1)
//			return false;

//		String url = "https://bepalingen.nhg.org/labcodes/determinations?page=" + iPage_;
//		driver.get(url);
		try {

//			list.stream()
//			.filter(a->a.getAttribute("href").endsWith("page="+iPage_))
//			.forEach(a->a.click());
			List<WebElement> list = driver.findElements(By.cssSelector("a.page-link"));
			for (WebElement a : list) {
				String href = a.getAttribute("href");
				System.out.println(href);
				if (href.endsWith("page=" + iPage_)) {
//					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", a);//					
					System.out.println("a.click()");
					a.click();
					Thread.sleep(5000);
					return true;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}
