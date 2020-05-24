package encaps.demo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import encaps.demo.model1.Model1;
import encaps.demo.model1.Model1Repo;
import encaps.demo.model1.Model2;
//import encaps.demo.model1.Model2Repo;
import encaps.demo.model1.Model3;

@SpringBootTest
class DemoApplicationTests {
	
	private static final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

	@Autowired
    private Model1Repo repo1;
	
//	@Autowired
//    private Model2Repo repo2;	
	
	@Test
	void testFindAll() {
		System.out.println(repo1.findAll());
	}
	
	@Test
	void testSave() {
		repo1.save(new Model1());		
	}
	
	@Test
	void testSave2() {
		Model1 m1 = new Model1();
		m1.setTitle("asdf");
		repo1.save(m1);
		log.info(m1.toString());
		
		Model2 m2 = new Model2();
		m2.setTitle("title21");
		m2.setModel1(m1);
//		repo2.save(m2);
		log.info(m2.toString());
					
	}	
	
	@Test
	void testSave3() {
		Model1 m1 = new Model1();
		m1.setTitle("asdf");
		repo1.save(m1);
		log.info(m1.toString());
		
		Model2 m2 = new Model2();
		m2.setTitle("title21");
		m2.setModel1(m1);
//		repo2.save(m2);
		log.info(m2.toString());			

		Model2 m22 = new Model2();
		m22.setTitle("title22");
		m22.setModel1(m1);
//		repo2.save(m22);
		log.info(m2.toString());
		
		m1.addModel2(m2);
		m1.addModel2(m22);
		repo1.save(m1);
		log.info(m1.toString());
	}		
	
	@Test
	void testFind() {
		Model1 m1;
		Optional<Model1> om = repo1.findById(3L);			
		if(om.isPresent()) {
			m1 = om.get();
			log.info(m1.toString());
		}
	}	
	
	@Test
	void testDelete() {
		Model1 m1;
		Optional<Model1> om = repo1.findById(10L);			
		if(om.isPresent()) {
			m1 = om.get();
			log.info(m1.toString());
			repo1.delete(m1);
			log.info(m1.toString());
		}
	}		
	
	@Test
	void testSave4() {
		Model1 m1 = new Model1("Model1");
			
		Model2 m22 = new Model2("Model2.2");
		m22.addModel3(new Model3("Model3"));
		m22.addModel3(new Model3("Model3.2"));
		m1.addModel2(m22);
		
		Model2 m2 = new Model2("Model2");
		m1.addModel2(m2);
		
		
						
		repo1.save(m1);
		log.info(m1.toString());
	}

}
