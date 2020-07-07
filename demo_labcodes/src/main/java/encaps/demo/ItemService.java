package encaps.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemService {

	@Autowired
	ItemRepo repoItem;

	public String csv() {
		String csv = "";
		StringBuilder sb = new StringBuilder();

		Iterable<Item> items = repoItem.findAll();
		int i = 0;
		for (Item item : items) {
			String s = "";
			s += wrap(item.getTitle()) + ",";
			s += "\r\n";
			sb.append(s);
			if (++i > 10)
				break;
		}

		String head = "Nr," + "Category," + "Subcategory," + "Vessel type," + "Price," + "Contact name," + "Telephone,"
				+ "Mobile," + "Fax," + "Website of seller\n";
		return head + sb.toString();
	}

	String wrap(String s) {
		return '"' + s + '"';
	}
}
