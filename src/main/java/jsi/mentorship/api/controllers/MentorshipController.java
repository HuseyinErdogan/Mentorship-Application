package jsi.mentorship.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
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
import jsi.mentorship.models.concretes.Mentorship;
import jsi.mentorship.models.concretes.Phase;

//@RestController
//@RequestMapping("/api/mentorship")
@Configuration
@CrossOrigin(origins = "http://localhost:6006")
@RestController
@RequestMapping("/api")
public class MentorshipController {
	
	@Autowired
	private MentorshipRepository mentorshipRepository;
	
	@Secured({"ROLE_USER","ROLE_MANAGER"})
	@GetMapping("/mentorships")
	public List<Mentorship> getAllMentorship(){
		return mentorshipRepository.findAll();
	}
	
	@Secured("ROLE_MANAGER")
	@PostMapping(value = "/mentorships/add")
	public String addMentorship(@RequestBody Mentorship mentorship) {
		mentorshipRepository.save(mentorship);
		return "işlem başarılı";
	}
	



}
