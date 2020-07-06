package encaps.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategCtl {
	
	@Autowired
	CategRepo repo;
	
	@Autowired
	ItemRepo repoProd;	
	
	@GetMapping("/state")
	Iterable<Categ> state(){
		return repo.findAll();
	}
	
	@GetMapping("/product")
	Iterable<Item> product(){
		return repoProd.findAll();
	}	
}
