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
import org.springframework.web.bind.annotation.RestController;

import jsi.mentorship.business.abstracts.MentorshipService;

import jsi.mentorship.models.concretes.Mentorship;



@Configuration
@CrossOrigin(origins = "http://localhost:6006")
@RestController
@RequestMapping("/api")
public class MentorshipController {
    public static final String ROLE_ADMIN = "ROLE_MANAGER";
    public static final String ROLE_USER = "ROLE_USER";
	
	@Autowired
	private MentorshipService mentorshipService;
	
	@Secured({ROLE_USER, ROLE_ADMIN})
	@GetMapping("/mentorships")
	public List<Mentorship>  getAllMentorships(){
		return this.mentorshipService.findAll();
	}
	
	@Secured(ROLE_ADMIN)
	@GetMapping("/mentorships/getByMenteeId/{id}")
	public List<Mentorship> getByMenteeId(@PathVariable("id") int id){
		return this.mentorshipService.findByMenteeId(id);
	}
	
	@Secured(ROLE_ADMIN)
	@GetMapping("/mentorships/getByMentorId/{id}")
	public List<Mentorship> getByMentorId(@PathVariable("id") int id){
		return this.mentorshipService.findByMentorId(id);
	}
	
	
	@Secured(ROLE_ADMIN)
	@PostMapping("/mentorships/add")
	public String addMentorship(@RequestBody Mentorship mentorship) {
		this.mentorshipService.saveOrUpdateMentorship(mentorship);
		return "işlem başarılı";
	}
	

	

	



}
