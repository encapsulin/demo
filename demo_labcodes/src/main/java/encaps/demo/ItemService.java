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
			
			if(item.getScol1() == null || item.getScol1().isEmpty() || item.getCol5().isEmpty())
				continue;
			
			String s = "";
			s += wrap(item.getCol1()) + ",";
			s += wrap(item.getCol2()) + ",";
			s += wrap(item.getCol3()) + ",";
			s += wrap(item.getCol4()) + ",";
			s += wrap(item.getCol5()) + ",";
			s += wrap(item.getCol6()) + ",";
			s += wrap(item.getCol7()) + ",";
			s += wrap(item.getCol8()) + ",";
			s += wrap(item.getCol9()) + ",";
			s += wrap(item.getCol10()) + ",";
			s += wrap(item.getCol11()) + ",";
			s += wrap(item.getCol12()) + ",";
			s += wrap(item.getCol13()) + ",";
			s += wrap(item.getCol14()) + ",";
			s += wrap(item.getCol15()) + ",";	
			s += wrap(item.getCol16()) + ",";
			s += wrap(item.getCol17()) + ",";
			s += wrap(item.getCol18()) + ",";
			s += wrap(item.getCol19()) + ",";
			s += wrap(item.getCol20()) + ",";
			s += wrap(item.getCol21()) + ",";
			s += wrap(item.getCol22()) + ",";
			s += wrap(item.getScol1()) + ",";
			s += wrap(item.getScol2()) + ",";
			s += wrap(item.getScol3()) + ",";
			s += wrap(item.getScol4()) + ",";
			s += wrap(item.getScol5()) + ",";
			s += "\r\n";
			sb.append(s);
			if (++i > 1000)
				break;
		}

		String head = "Nr.,Memo,Mat.,Bijz.,Omschrijving,Kort,AUB,Hoofd​aanvrg,Soort,Groep,Select.,VO,VM,VV,Toe​lichting,Type,Dec.,Een​heid,Abs​ min,Abs ​max,Ref ​min,Ref ​max,Antwoordenlijst,";
		head += "Toelichting,Antwoorden,Formule,Formuleuitleg,Clusters\n";		
		return head + sb.toString();
	}

	String wrap(String s) {
		return '"' + s + '"';
	}
}
