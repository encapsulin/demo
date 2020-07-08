package encaps.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

//		String fn = findFile("332");
////		System.out.println(fn);
////
//		Item item = new Item();
//		item.setCol1("332");		
//		ItemParser.parseItemFile(item);
//		System.out.println(item);
	}

	public static String findFile(String s) throws Exception {
		System.out.println("findFile()" + s);
		String fn = "";
		Optional<Path> path = Files.list(Paths.get("in/"))
//		.peek(p -> System.out.println("[" + p + "]"))
				.filter(p -> p.toString().endsWith("_item_" + s + ".html")).findAny();
		if (path.isPresent())
			fn = path.get().toString();

		return fn;
	}

	public static Item parseItemFile(Item item) throws Exception {
		
		String fn = ItemParser.findFile(item.getCol1());

		System.out.println("parseItemFile()" + fn);
//		Item item = new Item();
		if (!fn.isEmpty()) 
		try {
			String html = FilesUtils.read(fn);
			Document doc = Jsoup.parse(html);
			Elements elems = doc.select("side.labcodes-bottom div.row div");
			item.setScol1(elems.get(0).selectFirst("p").text());
			item.setScol2(elems.get(1).selectFirst("p").text());
			item.setScol3(elems.get(3).select(".row").get(0).selectFirst("p").text());
			item.setScol4(elems.get(2).select(".row").get(1).selectFirst("p").text());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		item.setDone(true);
		return item;
	}

	public static List<Item> parseItems(String html) {
		Document doc = Jsoup.parse(html);
		Elements elems = doc.select("div.table-container table tbody tr");
		List<Item> listItem = new ArrayList<>();

		for (Element elem : elems) {
			Item item = parseItem(elem);
			listItem.add(item);
		}

		return listItem;
	}

	public static Item parseItem(Element elem) {
		Item item = new Item();
		item.setCol1(elem.select("td:eq(0)").text());
		item.setCol2(elem.select("td:eq(1)").text());
		item.setCol3(elem.select("td:eq(2)").text());
		item.setCol4(elem.select("td:eq(3)").text());
		item.setCol5(elem.select("td:eq(4)").text());
		item.setCol6(elem.select("td:eq(5)").text());
		item.setCol7(elem.select("td:eq(6)").text());
		item.setCol8(elem.select("td:eq(7)").text());
		item.setCol9(elem.select("td:eq(8)").text());
		item.setCol10(elem.select("td:eq(9)").text());
		item.setCol11(elem.select("td:eq(10)").text());
		item.setCol12(elem.select("td:eq(10)").text());
		item.setCol13(elem.select("td:eq(12)").text());
		item.setCol14(elem.select("td:eq(13)").text());
		item.setCol15(elem.select("td:eq(14)").text());
		item.setCol16(elem.select("td:eq(15)").text());
		item.setCol17(elem.select("td:eq(16)").text());
		item.setCol18(elem.select("td:eq(17)").text());
		item.setCol19(elem.select("td:eq(18)").text());
		item.setCol20(elem.select("td:eq(19)").text());
		item.setCol21(elem.select("td:eq(20)").text());
		item.setCol22(elem.select("td:eq(21)").text());
		return item;
	}

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
