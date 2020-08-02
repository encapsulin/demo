package encaps.demoli;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class DemoliApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoliApplication.class, args);
	}

//	@Bean
//	public WebDriver webDriver() {
//	    return new FirefoxDriver();
//	}
}
