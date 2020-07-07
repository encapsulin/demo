package encaps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import encaps.demo.Categ;
import encaps.demo.CategRepo;
import encaps.demo.Item;
import encaps.demo.ItemRepo;

@Service
public class DemoService {

	@Autowired
	CategRepo repoCateg;

	@Autowired
	ItemRepo repoItem;

	public String csv() {
		String csv = "";
		StringBuilder sb = new StringBuilder();
		Iterable<Categ> categs1 = repoCateg.findByParentIdNull();
		int i = 0;
		LEVEL1: for (Categ categ1 : categs1) {

			Iterable<Categ> categs2 = repoCateg.findByParentId(categ1.getId());
			for (Categ categ2 : categs2) {

				Iterable<Item> items = repoItem.findByCategId(categ2.getId());

				for (Item item : items) {
					String s = "";
					s += wrap(item.getHref()) + ",";
					s += wrap(categ1.getTitle()) + ", ";
					s += wrap(categ2.getTitle()) + ",";
					s += wrap(item.getType()) + ",";
					s += wrap(item.getPrice()) + ",";
					s += wrap(item.getSelerName()) + ",";
					s += wrap(item.getSelerTelephone()) + ",";
					s += wrap(item.getSelerMobile()) + ",";
					s += wrap(item.getSelerFax()) + ",";
					s += wrap(item.getSelerWebsite()) + ",";
					s += "\r\n";
					sb.append(s);
					if (++i > 1000)
						break LEVEL1;
				}

			}

		}
		String head = "URL of boat," + 
				"Category," + 
				"Subcategory," + 
				"Vessel type," + 
				"Price," + 
				"Contact name," + 
				"Telephone," + 
				"Mobile," + 
				"Fax," + 
				"Website of seller\n";
		return head + sb.toString();
	}
	
	String wrap(String s){
		return '"' + s + '"';
	}
}
