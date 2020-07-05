package encaps.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import encaps.utils.FilesUtils;

public class CategParser {

	public static void main(String[] args) throws Exception {
		String fn = "tmp/index.html";
		String html = FilesUtils.read(fn);
		Document doc = Jsoup.parse(html);
		List<Categ> listCat = CategParser.parseCategs(doc);
		System.out.println(listCat);

	}

//	public static String getCategsHtml(String html) {
//		html = html.replaceFirst("^.*div id=, replacement)
//		return "";
//	}
//	
	public static List<Categ> parseCategs(Document doc) {
		List<Categ> listCat = new ArrayList<>();//
		Element elem = doc.selectFirst("div#leftSidebar");
//		FilesUtils.save(elem.html());
		Elements elems = elem.select("a[href^=/boats]");
		String www = "https://m.apolloduck.com";
//		listCat = elems.stream().peek(el -> System.out.println(el.outerHtml())).limit(3)
//				.map(el -> {
//			Categ categ = new Categ();
//			categ.setHref(www + el.attr("href"));
//			el.selectFirst("span").remove();
//			categ.setTitle(el.text());
//			return categ;
//
//		}).collect(Collectors.toList());

		int lim = 3;
		for (Element el : elems) {
			System.out.println(el.outerHtml());
			Categ categ = new Categ();
			categ.setHref(www + el.attr("href"));
			el.selectFirst("span").remove();
			categ.setTitle(el.text());
			listCat.add(categ);
//			if (--lim <= 0)
//				break;
		}
		return listCat;
	}

}
