package encaps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import encaps.utils.FilesUtils;

public class Parser {

	static String fileName = "AmericanWineProducers.csv";
	
	public static void main(String[] args) throws Exception {

		FilesUtils.write(fileName,"Name, Type, Number of wines, Address");
		parse();

	}

	static void parse() throws Exception {
		for (int page = 1; page <= 542; page++) {
			String fn = String.format("./tmp/American Wine Producers - page %d of 542..html", page);
			System.out.println(fn);
			try {
			String html = new String(Files.readAllBytes(Paths.get(fn)));
			Document doc = Jsoup.parse(html);

			StringBuilder sb = new StringBuilder();

			Elements els = doc.select("table[id=merchantsearch] tr");
			for (Element el : els) {
				String html1 = el.html();
				System.out.println(html1);
				if (html1.contains("USA.gif") || html1.contains("Page"))
					continue;

				Elements elsTds = el.select("td");
				String col2 = parseRegex(elsTds.get(2).text(),"(Wines:\\d+)");
				String s = String.format("\r\n\"%s\",\"%s\",\"%s\",\"%s\"", elsTds.get(0).text(), elsTds.get(1).text(), col2, elsTds.get(3).text());
				System.out.println(s);
				sb.append(s);
				FilesUtils.append(fileName,s);
			}

			System.out.println(sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
//			break;
		}
	}

	public static String parseRegex(String sHtml, String p_) {
		Pattern p = Pattern.compile(p_,
				Pattern.DOTALL);
		Matcher m = p.matcher(sHtml);
		if (m.find()) {
			return Jsoup.parse(m.group(1)).text();
//			return m.group(1);
		}
		return "";
	}
}
