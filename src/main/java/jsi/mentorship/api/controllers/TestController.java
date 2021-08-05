package jsi.mentorship.api.controllers;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Configuration
@CrossOrigin()
@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@GetMapping("/all")
	public String testPublicBoard(){
		return "PUBLIC BOARD";
	}
	
	@GetMapping("/user")
	public String testUser(){
		return "USER BOARD";
	}
	@GetMapping("/admin")
	public String testAdmin(){
		return "ADMIN BOARD";
	}	
	@GetMapping("/mod")
	public String testMod(){
		return "MOD BOARD";
	}
}
