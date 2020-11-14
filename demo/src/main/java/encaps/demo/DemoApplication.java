package encaps.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import encaps.demo.model1.Model1;
import encaps.demo.model1.Model1Controller;
import encaps.demo.model1.Model1Repo;
import encaps.demo.model1.Model2;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {		
		System.out.println("-= START =-");
//
//		Model1 m1 = new Model1();
//		m1.setTitle("asdf");
//		
//		Model2 m21 = new Model2();
//		m21.setTitle("title21");
//		
//		Model2 m22 = new Model2();
//		m22.setTitle("title22");
//		
////		m1.addModel2(m21);
//		//m1.addModel2(m22);
//	
//		repo.save(m1);
//		log.info(m1.toString());
//		
//		
		System.out.println("-= STOP =-");
	}
//	
//	@GetMapping
////	public @ResponseBody sqrt(@RequestParam(value="v") String sVal) {
//		public  Map<String, String> sqrt(@RequestParam(value="v") String sVal) {
//				
//		Map<String,String> aMap = new HashMap<>();
//		aMap.put(sVal, "value");
//		List<String> aList = new ArrayList<>();
//		aList.add("asdf");
//		return aMap;
//	}	
//	
}
