package encaps;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import encaps.demo.Categ;
import encaps.demo.CategRepo;
import encaps.demo.ItemRepo;

@RestController
public class DemoCtl {

	@Autowired
	DemoService demoService;

	
	@GetMapping("/csv")
	String csv() {
		return demoService.csv();
	}
}
