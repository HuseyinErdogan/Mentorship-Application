package jsi.mentorship.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsi.mentorship.business.abstracts.UserService;
import jsi.mentorship.core.utilities.results.DataResult;
import jsi.mentorship.core.utilities.results.Result;
import jsi.mentorship.models.concretes.User;


@Configuration
@CrossOrigin()
@RestController
@RequestMapping("/api")
public class UserController {
	
    @Autowired
    private UserService userService;
    
	@GetMapping("/users")
	public DataResult<List<User>> getAllUsers(){
		return this.userService.findAll();
	}

	@GetMapping("/users/get/{userId}")
	public DataResult<User> getById(@PathVariable("userId") int userId){
		return this.userService.findByUserId(userId);
	}
	
	@GetMapping("/users/getByUsername/{username}")
	public DataResult<User> getByUsername(@PathVariable("username") String username){
		return this.userService.findByUsername(username);
	}
	
	@GetMapping("/users/getByGoogleAuth/{email}")
	public DataResult<User> getByGoogleAuth(@PathVariable("email") String email){
		return this.userService.findByEmail(email);
	}
	
	
	@PostMapping("/users/add")
	public Result addUser(@RequestBody User user) {
		return this.userService.saveOrUpdateUser(user);
	}
	
	@GetMapping("/users/mentors")
	public DataResult<List<User>> getAllMentors(){
		return this.userService.findMentors();
	}
	
	@GetMapping("/users/getBySubsubject/{subsubjectName}")
	public DataResult<List<User>> getAllMentorsBySubsubject(@PathVariable("subsubjectName") String subsubjectName){
		return this.userService.findAllMentorsBySubsubject(subsubjectName);
	}
	
	@GetMapping("/users/getByDescription/{description}")
	public DataResult<List<User>> getMentorsByDescription(@PathVariable("description") String description){
		return this.userService.findMentorsByDescription(description);
	}
	
	
	
}
