package jsi.mentorship.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsi.mentorship.dataAccess.SubjectRepository;
import jsi.mentorship.models.concretes.Subject;
import jsi.mentorship.models.concretes.Subsubject;

@Configuration
@CrossOrigin(origins = "http://localhost:6006")
@RestController
@RequestMapping("/api")
public class SubjectController {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Secured({"ROLE_USER","ROLE_MANAGER"})
	@GetMapping("/subjects")
	public List<Subject> getAllSubject(){
		return this.subjectRepository.findAll();
	}
	


}
