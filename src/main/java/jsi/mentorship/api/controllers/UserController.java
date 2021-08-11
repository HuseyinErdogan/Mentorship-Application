package jsi.mentorship.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jsi.mentorship.business.abstracts.UserService;
import jsi.mentorship.models.concretes.Subsubject;
import jsi.mentorship.models.concretes.User;


@Configuration
@CrossOrigin()
@RestController
@RequestMapping("/api")
public class UserController {
	
    public static final String ROLE_ADMIN = "ROLE_MANAGER";
    public static final String ROLE_USER = "ROLE_USER";
	
    @Autowired
    private UserService userService;
    
    
	//@Secured({ROLE_ADMIN})
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return this.userService.findAll();
	}

    
	

	//@Secured({ROLE_USER, ROLE_ADMIN})
	@GetMapping("/users/get/{userId}")
	public User getById(@PathVariable("userId") int userId){
		return userService.findByUserId(userId);
	}
	
	@GetMapping("/users/getByUsername/{username}")
	public User getByUsername(@PathVariable("username") String username){
		return userService.findByUsername(username);
	}
	
	//@Secured(ROLE_ADMIN)
	@PostMapping("/users/add")
	public String addUser(@RequestBody User user) {
		this.userService.saveOrUpdateUser(user);
		return "işlem başarılı";
	}

	
}
