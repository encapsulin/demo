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
		for (Categ categ1 : categs1) {

			Iterable<Categ> categs2 = repoCateg.findByParentId(categ1.getId());
			for (Categ categ2 : categs2) {
				String s = categ1.getTitle() + ", ";
				s += categ2.getTitle() + ",";
				
				Iterable<Item> items = repoItem.findByCategId(categ2.getId());
				int i = 0;
				for (Item item : items) {					
					s += item.getTitle() + "\r\n";
					sb.append(s);
					if(++i>3)
						break;
				}				
				
			}

		}
		return sb.toString();
	}
}
