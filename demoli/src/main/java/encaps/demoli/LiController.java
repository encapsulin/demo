package encaps.demoli;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LiController {
	
	@GetMapping("/")
	public String home() {
		return "asdfy";
	}
}
