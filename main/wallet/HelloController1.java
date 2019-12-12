package main.wallet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController1 {

	@RequestMapping({"/","/hellow"})
	public String hello(){
		return "hello wallet!";
		
	}
}
