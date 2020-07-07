package encaps.demo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ItemCtl {

	@Autowired
	ItemService itemService;
	
	@GetMapping("/csv")
	String csv() {
		return itemService.csv();
	}
}
