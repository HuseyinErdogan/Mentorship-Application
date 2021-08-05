package jsi.mentorship.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jsi.mentorship.dataAccess.SubjectRepository;
import jsi.mentorship.models.concretes.Subject;

@Configuration
@CrossOrigin(origins = "http://localhost:6006")
@RestController
@RequestMapping("/api")
public class SubjectController {
	
    public static final String ROLE_ADMIN = "ROLE_MANAGER";
    public static final String ROLE_USER = "ROLE_USER";
	
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	//@Secured({ROLE_ADMIN,ROLE_USER})
	@GetMapping("/subjects")
	public List<Subject> getAllSubjects(){
		return this.subjectRepository.findAll();
	}
	
	//@Secured(ROLE_ADMIN)
	@PostMapping("/subjects/add") 
	public String addSubject(@RequestBody Subject subject) {
		this.subjectRepository.save(subject);
		return subject.getSubjectName()+" eklendi.";
	}


}
