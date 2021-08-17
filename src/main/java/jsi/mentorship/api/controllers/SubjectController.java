package jsi.mentorship.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsi.mentorship.business.abstracts.SubjectService;
import jsi.mentorship.core.utilities.results.DataResult;
import jsi.mentorship.core.utilities.results.Result;
import jsi.mentorship.models.concretes.Subject;
import jsi.mentorship.models.wrappers.SubjectSubsubject;

@Configuration
@CrossOrigin()
@RestController
@RequestMapping("/api")
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@GetMapping("/subjects")
	public DataResult<List<Subject>>getAllSubjects(){
		return this.subjectService.findAll();
	}
	
	@PostMapping("/subjects/add") 
	public Result addSubject(@RequestBody Subject subject) {
		return this.subjectService.saveOrUpdateSubject(subject);
	}
	
	@PostMapping("/subjects/addSubsubject/")
	public Result addSubsubjectToSubject(@RequestBody SubjectSubsubject subjectSubsubject){
		return this.subjectService.addSubsubjectToSubject(subjectSubsubject);
	}
	
	@DeleteMapping("/subjects/deleteSubsubject/")
	public Result deleteSubsubject(@RequestBody SubjectSubsubject subjectSubsubject) {
		return this.subjectService.deleteSubsubject(subjectSubsubject);
	}
	
	@DeleteMapping("/subjects/delete/{subjectName}")
	public Result deleteSubjectBySubjectName(@PathVariable("subjectName") String subjectName) {
		return this.subjectService.deleteSubjectBySubjectName(subjectName);
	}
	
	

}
