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

import jsi.mentorship.business.abstracts.MentorshipService;
import jsi.mentorship.core.utilities.results.DataResult;
import jsi.mentorship.core.utilities.results.Result;
import jsi.mentorship.models.concretes.Mentorship;
import jsi.mentorship.models.concretes.User;
import jsi.mentorship.models.wrappers.MentorshipPhases;



@Configuration
@CrossOrigin()
@RestController
@RequestMapping("/api")
public class MentorshipController {
	@Autowired
	private MentorshipService mentorshipService;
	@GetMapping("/mentorships")
	public DataResult<List<Mentorship>>  getAllMentorships(){
		return this.mentorshipService.findAll();
	}
	
	@GetMapping("/mentorships/get/{id}")
	public DataResult<Mentorship> getById(@PathVariable("id") int id){
		return this.mentorshipService.findById(id);
	}
	
	@GetMapping("/mentorships/getByMenteeId/{menteeId}")
	public DataResult<List<Mentorship>> getByMenteeId(@PathVariable("menteeId") int menteeId){
		return this.mentorshipService.findByMenteeId(menteeId);
	}
	
	@GetMapping("/mentorships/getByMentorId/{mentorId}")
	public DataResult<List<Mentorship>> findByMentorId(@PathVariable("mentorId") int mentorId){
		return this.mentorshipService.findByMentorId(mentorId);
	}

	@PostMapping("/mentorships/add")
	public Result addMentorship(@RequestBody Mentorship mentorship) {
		return this.mentorshipService.saveOrUpdateMentorship(mentorship);	
	}
	
	@GetMapping("/mentorships/getMentor/{id}")
	public DataResult<User> getMentorByMentorshipId(@PathVariable("id") int id){
		return this.mentorshipService.findMentorFromMentorshipById(id);
	}
	
	@GetMapping("/mentorships/getMentee/{id}")
	public DataResult<User> getMenteeByMentorshipId(@PathVariable("id") int id){
		return this.mentorshipService.findMenteeFromMentorshipById(id);
	}
	
	@PostMapping("/mentorships/addPhases")
	public Result addPhasesToMentorship(@RequestBody MentorshipPhases mentorshipPhases) {
		return this.mentorshipService.addPhasesToMentorship(mentorshipPhases);
	}	
	
	@GetMapping("/mentorships/getOldMentorships/{mentorId}")
	public DataResult<List<Mentorship>> getMentorsOldMentorships(@PathVariable("mentorId") int mentorId){
		return this.mentorshipService.findMentorsOldMentorships(mentorId);
	}
	@GetMapping("/mentorships/getActivesByMentor/{mentorId}")
	public DataResult<List<Mentorship>> getMentorsActiveMentorships(@PathVariable("mentorId") int mentorId){
		return this.mentorshipService.findMentorsActiveMentorships(mentorId);
	}
	
	@GetMapping("/mentorships/getActivesByMentee/{menteeId}")
	public DataResult<List<Mentorship>> getMenteesActiveMentorships(@PathVariable("menteeId") int menteeId){
		return this.mentorshipService.findMenteesActiveMentorships(menteeId);
	}
}
