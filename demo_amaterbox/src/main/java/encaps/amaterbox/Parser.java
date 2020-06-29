package encaps.amaterbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import encaps.utils.FilesUtils;

public class Parser {

	public static void main(String[] args) {

//		String sHtml = FilesUtils.read("log/1592143438243.html");
//		List<State> states = parseStates(sHtml);
//		System.out.println(states);

		 String sHtml = FilesUtils.read("log/1592147833854.html");
		 sHtml = parseProductsSection(sHtml);
		 System.out.println(sHtml);
		 List<Item> products = parseProducts(sHtml);
		 //System.out.println(products);

//		String sHtml = FilesUtils.read("log/section.html");
//		Product product = parseProduct(sHtml);
//		System.out.println(product);

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
		
		p = Pattern.compile("<p.*</p>.*<p.*/maps.*.*<br.*\"mailto:.+?\".*Website:[^<]*<a href=\"(.+?)\"", Pattern.DOTALL);
//		prod.setWebsite(parseProduct(sHtml,p));
		
		p = Pattern.compile("<p.*</p>.*<p.*/maps.*.*<br.*\"mailto:.+?\".*\"(http://www.facebook.com.+?)\"", Pattern.DOTALL);
//		prod.setFacebook(parseProduct(sHtml,p));

		p = Pattern.compile("<p.*</p>.*<p.*/maps.*.*<br.*\"mailto:.+?\".*\"(http://www.instagram.com.+?)\"", Pattern.DOTALL);
//		prod.setInstagram(parseProduct(sHtml,p));
		
		return prod;
	}
	
	public static String parseProduct(String sHtml,Pattern p) {
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
			//System.out.println(s);
			Item prod = parseProduct(s);					
			System.out.println(prod);
//			if(!prod.getTitle().trim().isEmpty())
//				list.add(prod);	
//			if(i-- <= 0)
//				break;
		}
		//return list.stream().limit(5).collect(Collectors.toList());
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
