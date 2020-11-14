package encaps.demo.model1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import encaps.demo.model1.*;

//@RestController
@Controller
@RequestMapping("/model1")
public class Model1Controller {

	private static final Logger log = LoggerFactory.getLogger(Model1Controller.class);

	@Autowired
	private Model1Repo repo;

	@GetMapping("/rest")
//	public @ResponseBody sqrt(@RequestParam(value="v") String sVal) {
	public Iterable<Model1> all() {
		Iterable<Model1> all = repo.findAll();
		log.info(all.toString());
		return all;
	}
	
	@GetMapping("/allS")
//	public @ResponseBody sqrt(@RequestParam(value="v") String sVal) {
	public String allS() {
		Iterable<Model1> all = repo.findAll();
		log.info(all.toString());
		return all.toString();
	}	
	
	@GetMapping
	public String get() {
		return "model1";
	}
	
	@PostMapping
	public String post(Model4 model) {
		System.out.println(model);
		return "model1";
	}

}
