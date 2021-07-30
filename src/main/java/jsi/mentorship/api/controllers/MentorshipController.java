package jsi.mentorship.api.controllers;

import java.util.ArrayList;
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
import jsi.mentorship.dataAccess.MentorshipRepository;
import jsi.mentorship.dataAccess.SubjectRepository;
import jsi.mentorship.models.concretes.Mentorship;
import jsi.mentorship.models.concretes.Phase;
import jsi.mentorship.models.concretes.Subsubject;


@Configuration
@CrossOrigin(origins = "http://localhost:6006")
@RestController
@RequestMapping("/api")
public class MentorshipController {
    public static final String ROLE_ADMIN = "ROLE_MANAGER";
    public static final String ROLE_USER = "ROLE_USER";
	
	@Autowired
	private MentorshipRepository mentorshipRepository;
	
	@Secured({ROLE_USER, ROLE_ADMIN})
	@GetMapping("/mentorships")
	public List<Mentorship> getAllMentorship(){
		return mentorshipRepository.findAll();
	}
	
	@Secured({ROLE_USER, ROLE_ADMIN})
	@GetMapping("/mentorships/get/{id}")
	public Mentorship getById(@PathVariable int id){
		return mentorshipRepository.findById(id).get();
	}
	
	@Secured(ROLE_ADMIN)
	@PostMapping(value = "/mentorships/add")
	public String addMentorship(@RequestBody Mentorship mentorship) {
		mentorshipRepository.save(mentorship);
		return "işlem başarılı";
	}
	

	



}
