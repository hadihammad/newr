package main.welcome;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping({ "/" })
	public String hello() {
		return "Hello, this is the root, please use postman to test, https://www.getpostman.com/, to know all the links please go to: http://localhost:8080/swagger-ui.html";

	}
}
