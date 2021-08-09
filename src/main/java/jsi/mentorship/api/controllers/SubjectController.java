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

import jsi.mentorship.business.abstracts.SubjectService;
import jsi.mentorship.models.concretes.Subject;

@Configuration
@CrossOrigin()
@RestController
@RequestMapping("/api")
public class SubjectController {
	
    public static final String ROLE_ADMIN = "ROLE_MANAGER";
    public static final String ROLE_USER = "ROLE_USER";
	
	
	@Autowired
	private SubjectService subjectService;
	
	//@Secured({ROLE_ADMIN,ROLE_USER})
	@GetMapping("/subjects")
	public List<Subject> getAllSubjects(){
		return this.subjectService.findAll();
	}
	
	//@Secured(ROLE_ADMIN)
	@PostMapping("/subjects/add") 
	public String addSubject(@RequestBody Subject subject) {
		this.subjectService.saveOrUpdateSubject(subject);
		return "işlem başarılı";
	}
	
	//@Secured({ROLE_ADMIN,ROLE_USER})
	@GetMapping("/subjects/addSubsubject/{subjectName}/{subsubjectName}")
	public Subject addSubsubjectToSubject(@PathVariable("subjectName") String subjectName, @PathVariable("subsubjectName") String subsubjectName){
		return this.subjectService.addSubsubjectToSubject(subjectName, subsubjectName);
	}
	

}
