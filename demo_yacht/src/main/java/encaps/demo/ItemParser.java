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
//		Document doc = Jsoup.parse(new File("tmp/items.html"), null);
//		List<Item> listItem = ItemParser.parseItems(doc);
//		System.out.println(listItem);

//		String fn = "tmp/items.html";
//		String html = FilesUtils.read(fn);
//		List<Item> listItem = ItemParser.parseItems(html);
//		System.out.println(listItem);

		String fn = "tmp/itemType.html";
		String html = FilesUtils.read(fn);

		System.out.println(parseRegex(html,">Contact: </td><td.*?>(.+?)</td>"));
		System.out.println(parseRegex(html,">Fax: *</td><td.*?>(.+?)</td>"));
		System.out.println(parseRegex(html,">Website: *</td><td.*?href=\"(.+?)\"<"));
		
		String txt = FilesUtils.read("tmp/item.txt");
		System.out.println(ItemParser.parseRegex(txt, "Price: (.+?) "));
		
		String s = ItemParser.parseRegex(html, "Vessel type:</td>.*?>(.+?)</td>");
		System.out.println(s);

	}


	public static List<Item> parseItems(String html) {
		List<Item> listItem = new ArrayList<>();
		Document doc = Jsoup.parse(html);
		Element elem = doc.selectFirst("div.galleryPanels");
		Elements elems = elem.select("a[href*=/boat/][class=BasicTitle], a[href*=/boat/][class=FeatureTitle]");

		int lim = 3;
		for (Element el : elems) {
			System.out.println(el.outerHtml());
			Item item = new Item();
			item.setHref("https://m.apolloduck.com" + el.attr("href"));
			item.setTitle(el.text());
			listItem.add(item);
//			if (--lim <= 0)
//				break;
		}
		return listItem;
	}

	public static String parseRegex(String sHtml, Pattern p) {
		try {
			Matcher m = p.matcher(sHtml);
			if (m.find()) {
				return Jsoup.parse(m.group(1)).text();
//			return m.group(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	public static String parseRegex(String sHtml, String p) {
		return parseRegex(sHtml,Pattern.compile(p, Pattern.DOTALL));		
	}


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

	public static List<Categ> parseStates(String sHtml) {
		// TODO Auto-generated method stub
		// String sHtml = FilesUtils.read("log/1592143438243.html");
		Document doc = Jsoup.parse(sHtml);
		Elements as = doc.select("blockquote p a");
		// System.out.println(as);
		List<Categ> states = new ArrayList<>();
		for (Element link : as) {
			String linkHref = link.attr("href");
			String linkText = link.text();
			if (linkHref.startsWith("http") || linkText.trim().length() <= 1)
				continue;

			Categ state = new Categ();
			state.setTitle(linkText);
			state.setHref(linkHref);
			states.add(state);
		}
		return states;
	}

}
