package jsi.mentorship.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsi.mentorship.dataAccess.MentorshipRepository;
import jsi.mentorship.models.concretes.Mentorship;
import jsi.mentorship.models.concretes.Phase;

@RestController
@RequestMapping("/api/mentorship")
public class MentorshipController {
	
	@Autowired
	private MentorshipRepository mentorshipRepository;
	
	@GetMapping(value = "/getAll")
	public List<Mentorship> getAllMentorship(){
		return mentorshipRepository.findAll();
	}
	
	@PostMapping(value = "/add")
	public String addMentorship(@RequestBody Mentorship mentorship) {
//		
//		Phase ph = new Phase();
//		ph.setName("Test");
//		ph.setPhaseNumber(1);
//		
//		ArrayList<Phase> phArray = new ArrayList();
//		phArray.add(ph);
//		mentorship.setPhases(phArray);
		mentorshipRepository.save(mentorship);
		return "işlem başarılı";
	}

}
