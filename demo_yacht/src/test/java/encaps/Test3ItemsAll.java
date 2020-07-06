package encaps;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import encaps.utils.FilesUtils;
import encaps.utils.OCR;

@SpringBootTest
class Test3ItemsAll {

	@Autowired
	CategRepo repoCateg;

	@Autowired
	ItemRepo repoItem;

	WebDriver driver;

	@Test
	void contextLoads() throws Exception {
//		parseItem_(2);
		parseItems();
	}

	void parseItems() throws Exception {

		if (driver == null)
			driver = new ChromeDriver();

		//Iterable<Item> list = repoItem.findByPriceNull();
		Iterable<Item> list = repoItem.findAll();
		int i = 0;

		for (Item item : list) {
//			if (--lim <= 0)
//				break;
//			if (lim++ <= 8)
//				continue;

			parseItem(driver, item);
			//System.out.println(item);
			repoItem.save(item);
			System.out.println(++i);
		}

	}

	void parseItem_(int id) {
		if (driver == null)
			driver = new ChromeDriver();
		Optional<Item> opt = repoItem.findById(id);
		if (opt.isPresent()) {
			Item item = opt.get();
			parseItem(driver, item);
			System.out.println(item);
		}
	}

	public static void parseItem(WebDriver driver, Item item) {
//		Item item = new Item();
		String url = item.getHref();
		driver.get(url);

		String html = driver.getPageSource();
		// FilesUtils.save(html);

		Document doc = Jsoup.parse(html);

		String s = ItemParser.parseRegex(showFax(driver), "Fax: *(.+?)[a-zA-Z]");
		item.setSelerFax(s);

		String txt = doc.text();
		// FilesUtils.save(txt);

		s = ItemParser.parseRegex(txt, "Price: (.+?) ");
		item.setPrice(s);

		s = ItemParser.parseRegex(txt, "Mobile: ([^a-zA-Z]+?)[A-Za-z]");
		item.setSelerMobile(s);
		
		try {
			item.setTitle2(doc.selectFirst("div[class=advertTitle]").text());
			item.setTitle3(doc.selectFirst("div.imageView.cp").text());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		s = ItemParser.parseRegex(html, ">Contact: *</td><td.*?>(.+?)</td>");
		item.setSelerName(s);

		s = ItemParser.parseRegex(html, ">Telephone: *</td><td.*?>(.+?)</td>");
		item.setSelerTelephone(s);

		s = ItemParser.parseRegex(html, ">Website: *</td>.*?href=\"(.+?)\"");
		item.setSelerWebsite(s);

		s = ItemParser.parseRegex(html, "Vessel type:</td>.*?>(.+?)</td>");
		item.setType(s);

		System.out.println(item);

	}

	static String showFax(WebDriver driver) {
		try {
//			Thread.sleep(3000);
			List<WebElement> elems = driver.findElements(By.cssSelector("table.dealerContactTable tr"));
			for (WebElement elem : elems) {
				//System.out.println(elem.getAttribute("outerHTML"));
				List<WebElement> elemsTd = elem.findElements(By.cssSelector("td"));
				if (elemsTd.get(0).getText().contains("Fax:") && elemsTd.get(1).getText().contains("Reveal")) {
					WebElement elemA = elem.findElement(By.cssSelector("a"));
					System.out.println(elemA.getAttribute("outerHTML"));
//					elemA.click();
//					WebElement element = driver.findElement(By.id("id_of_element"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemA);
					Thread.sleep(500);
					Runtime.getRuntime().exec("xdotool mousemove 506 175 click 1");
					Thread.sleep(500);
					takeSnapShot(driver, "log/screen.png");
					return OCR.ocr("log/screen.png");
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File DestFile = new File(fileWithPath);
		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
	}

	void click(WebDriver driver, String css) {
		try {
//			String css = "div[class=callNowButton]";
			WebElement elem = driver.findElement(By.cssSelector(css));
			elem.click();
//			elem = driver.findElement(By.cssSelector(css));
//			String s = elem.getText();
//			item.setSelerTelephone(s);
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
			for (WebElement elem : elems) {
				if (elem.getText().contains("Next")) {
					elem.click();
					System.out.println("elem.click();");
					return true;
				}
			}
			;
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
