package encaps.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import encaps.utils.FilesUtils;

public class ItemParser {

	public static void main(String[] args) throws Exception {

		String fn = "tmp/items.html";
		String html = FilesUtils.read(fn);
		List<Item> listItem = ItemParser.parseItems(html);
		System.out.println(listItem);

	}

	public static List<Item> parseItems(String html) {
		Document doc = Jsoup.parse(html);
		Elements elems = doc.select("div.table-container table tbody tr");
		List<Item> listItem = new ArrayList<>();

		for (Element elem : elems) {
			Item item = parseItem(elem.outerHtml());
			listItem.add(item);
		}		

		return listItem;
	}

	public static Item parseItem(String html) {
	Item item = new Item();
	item.setCol1(parseRegex(html,"td>(.*?)</td",1));
	item.setCol2(parseRegex(html,"td>(.*?)</td",2));
	item.setCol3(parseRegex(html,"td>(.*?)</td",3));
	return item;
}
	
//	public static Item parseItem(String html) {
//		Document doc = Jsoup.parse(html);
//				Element elem = doc.selectFirst("tr");
//		Item item = new Item();
//		item.setCol1(elem.select("td:eq(0)").text());
//		item.setCol2(elem.select("td:eq(1)").text());
//		item.setCol3(elem.select("td:eq(2)").text());
//		item.setCol4(elem.select("td:eq(3)").text());
//		item.setCol5(elem.select("td:eq(5)").text());
//		return item;
//	}

	public static String parseRegex(String sHtml, String sPattern_, int group_) {
		try {
			Pattern p = Pattern.compile(sPattern_, Pattern.DOTALL);
			Matcher m = p.matcher(sHtml);
			if (m.find()) {
				return Jsoup.parse(m.group(group_)).text();
//			return m.group(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

//	public static String parseRegex(String sHtml, String p) {
//		return parseRegex(sHtml, Pattern.compile(p, Pattern.DOTALL));
//	}

	public static String parseProductsSection(String sHtml) {
		// sHtml = "public <static qwer> List<static asdf> parseProducts(String sHtml)
		// {";
		// System.out.println(sHtml);
		Pattern p = Pattern.compile("(<hr.*<p.*?>)", Pattern.DOTALL);
		// Pattern p = Pattern.compile("(<static.*?>)", Pattern.DOTALL);
		Matcher m = p.matcher(sHtml);
		// System.out.println(m.matches());
		while (m.find()) {
			System.out.println(m.group());
//			System.out.println(m.group(0));
			return m.group(1);
		}
		return null;
	}

}
