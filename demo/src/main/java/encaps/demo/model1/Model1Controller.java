package encaps.demo.model1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/model1")
public class Model1Controller {
	
	private static final Logger log = LoggerFactory.getLogger(Model1Controller.class);
	
	@Autowired
	private Model1Repo repo;
	
	@GetMapping("/")
//	public @ResponseBody sqrt(@RequestParam(value="v") String sVal) {
		public Model1 asdf() {
		
		Model1 m = new Model1();
		m.setTitle("asdf");
		log.info(m.toString());
		repo.save(m);
		log.info(m.toString());
		
		return m;
	}
	
	@GetMapping("/sqrt")
//	public @ResponseBody sqrt(@RequestParam(value="v") String sVal) {
		public  Map<String, String> sqrt(@RequestParam(value="v") String sVal) {
		
		Model1 m = new Model1();
		m.setTitle("asdf");
		repo.save(m);
		
		Map<String,String> aMap = new HashMap<>();
		aMap.put(sVal, "value");
		List<String> aList = new ArrayList<>();
		aList.add("asdf");
		return aMap;
	}
}
