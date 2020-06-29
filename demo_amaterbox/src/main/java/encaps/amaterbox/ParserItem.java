package encaps.amaterbox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserItem {

	public static void main(String[] args) throws Exception {
//		String html = " <p align=\"left\"><b>03/01/2020\n"
//				+ "     - in archive: </b>incomplete results of <a href=\"http://amateur-boxing.strefa.pl/Tournaments/Honved1978.html\">18.Honved\n"
//				+ "     Cup</a> in Budapest (HUN) in 1978</p>\n";
//		Categ categ = parseCateg(html);
//		System.out.println(categ);

		String s = "tmp/21.Friendship.html";
		Document doc = Jsoup.parse(new File(s), "UTF-8");
//		System.out.println(parseCategLocation(doc));
//		System.out.println(parseCategDate2(doc));

//		List<Element> list = doc.select("p + table");

		List<Item> listItems = parseItems(doc.html());
		System.out.println(listItems);
		
	}

	public static List<Item> parseItems(String html){
			List<String> list = parseSections(Jsoup.parse(html));
//			System.out.println(list.size());
			List<Item> listItems = new ArrayList<>();
			for (String html2 : list) {
				listItems.addAll(parseSectionItems(html2));
//				System.out.println(listItems);
			}
			return listItems;
		}
	 
	public static List<Item> parseSectionItems(String html) {
		Document doc = Jsoup.parse(html);
		String sSection = doc.select("p:eq(0)").text();
		List<Item> listItems = new ArrayList<>();
		List<Element> listElement = doc.select("tr");
		for (Element elem : listElement) {
			Item item = new Item();
			item.setNotes(sSection);
			item.setFighter_1_weight(elem.select("td:eq(0)").html());
			item.setFighter_1_name(elem.select("td:eq(1)").html());
			item.setFighter_1_country(elem.select("td:eq(2)").html());
			item.setFighter_2_name(elem.select("td:eq(3)").html());
			item.setFighter_2_country(elem.select("td:eq(4)").html());
			listItems.add(item);
		}
		return listItems;
	}

	public static List<String> parseSections(Document doc) {
//		System.out.println(doc.html());
		List<String> list = new ArrayList<>();
		Pattern p = Pattern.compile("(<p><b><i><.*?</table>)", Pattern.DOTALL);
		Matcher m = p.matcher(doc.html());
		while (m.find()) {
//			return Jsoup.parse(m.group(1)).text();
			list.add(m.group(1));
		}
		return list;
	}

	public static String parseCategLocation(Document doc) {
		return doc.select("p b").get(1).html();
	}

	public static String parseCategDate2(Document doc) {
		return doc.select("p b").get(2).html();
	}

	public static Categ parseCateg(String html) {
		Categ categ = new Categ();

		Pattern p = Pattern.compile("<b>([^\\s]+)", Pattern.DOTALL);
		String sDate = parseRegex(html, p);
//		LocalDate ld = LocalDate.parse(sDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		categ.setDate(sDate);

		p = Pattern.compile(">([^<]+)</a>", Pattern.DOTALL);
		categ.setTitle(parseRegex(html, p));

		p = Pattern.compile("href=\"([^\"]+)", Pattern.DOTALL);
		categ.setHref(parseRegex(html, p));

//		p = Pattern.compile("</a> in ([^ ]+)", Pattern.DOTALL);
//		categ.setLocation(parseRegex(html, p));

		return categ;
	}

	public static Item parseProduct(String sHtml) {
		Item prod = new Item();

		Pattern p = Pattern.compile("<strong>(.*?)</strong>");
//		prod.setTitle(parseProduct(sHtml,p));
//		if(prod.getTitle().isEmpty()) {
//			p = Pattern.compile("<p>(.*?)</p>");
//			prod.setTitle(parseProduct(sHtml,p));
//		}

		p = Pattern.compile("<p[^>]+>(.*)</p>.*<p.*/maps", Pattern.DOTALL);
//		prod.setDescr(parseProduct(sHtml, p));

		p = Pattern.compile("<p.*</p>.*<p[^>]+>(.*/maps.*).*<br.*mail:.*", Pattern.DOTALL);
//		prod.setContacts(parseProduct(sHtml,p));

		p = Pattern.compile("<p.*</p>.*<p.*/maps.*.*<br.*\"mailto:(.+?)\".*", Pattern.DOTALL);
//		prod.setEmail(parseProduct(sHtml,p));

		p = Pattern.compile("<p.*</p>.*<p.*/maps.*.*<br.*\"mailto:.+?\".*Website:[^<]*<a href=\"(.+?)\"",
				Pattern.DOTALL);
//		prod.setWebsite(parseProduct(sHtml,p));

		p = Pattern.compile("<p.*</p>.*<p.*/maps.*.*<br.*\"mailto:.+?\".*\"(http://www.facebook.com.+?)\"",
				Pattern.DOTALL);
//		prod.setFacebook(parseProduct(sHtml,p));

		p = Pattern.compile("<p.*</p>.*<p.*/maps.*.*<br.*\"mailto:.+?\".*\"(http://www.instagram.com.+?)\"",
				Pattern.DOTALL);
//		prod.setInstagram(parseProduct(sHtml,p));

		return prod;
	}

	public static String parseRegex(String sHtml, Pattern p) {
		Matcher m = p.matcher(sHtml);
		if (m.find()) {
			return Jsoup.parse(m.group(1)).text();
//			return m.group(1);
		}
		return "";
	}

	public static List<Item> parseProducts(String sHtml) {
		List<Item> list = new ArrayList<>();
		String[] a = sHtml.split("<hr ");
		int i = 5;
		for (String s : a) {
			System.out.println("***");
			// System.out.println(s);
			Item prod = parseProduct(s);
			System.out.println(prod);
//			if(!prod.getTitle().trim().isEmpty())
//				list.add(prod);	
//			if(i-- <= 0)
//				break;
		}
		// return list.stream().limit(5).collect(Collectors.toList());
		return list;
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
//			System.out.println(m.group());
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
