package jsi.mentorship.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsi.mentorship.dataAccess.MentorshipRepository;
import jsi.mentorship.dataAccess.UserRepository;
import jsi.mentorship.models.concretes.Mentee;
import jsi.mentorship.models.concretes.Mentor;
import jsi.mentorship.models.concretes.Subject;
import jsi.mentorship.models.concretes.User;


@Configuration
@CrossOrigin(origins = "http://localhost:6006")
@RestController
@RequestMapping("/api")
public class UserController {
	
    public static final String ROLE_ADMIN = "ROLE_MANAGER";
    public static final String ROLE_USER = "ROLE_USER";
	
    @Autowired
    private UserRepository userRepository;
    
    
	@Secured({ROLE_ADMIN,ROLE_USER})
	@GetMapping("/users")
	public List<User> getAllSubject(){
		return this.userRepository.findAll();
	}
	

	
}
