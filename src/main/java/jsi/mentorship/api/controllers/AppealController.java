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

import jsi.mentorship.business.abstracts.BecomeMentorAppealService;
import jsi.mentorship.business.abstracts.MentorshipAppealService;
import jsi.mentorship.business.abstracts.MentorshipService;
import jsi.mentorship.models.concretes.BecomeMentorAppeal;
import jsi.mentorship.models.concretes.Mentorship;
import jsi.mentorship.models.concretes.MentorshipAppeal;
import jsi.mentorship.models.concretes.User;

@Configuration
@CrossOrigin()
@RestController
@RequestMapping("/api/appeals")
public class AppealController {	
	
	 public static final String ROLE_ADMIN = "ROLE_MANAGER";
	    public static final String ROLE_USER = "ROLE_USER";
		
		@Autowired
		private BecomeMentorAppealService becomeMentorAppealService;
		@Autowired
		private MentorshipAppealService mentorshipAppealService;
		

		@GetMapping("/becomeMentor")
		public List<BecomeMentorAppeal>  getAllBecomeMentorAppeals(){
			return this.becomeMentorAppealService.findAll();
		}
		
		@GetMapping("/becomeMentor/get/{id}")
		public BecomeMentorAppeal getBecomeMentorAppealById(@PathVariable("id") int id){
			return this.becomeMentorAppealService.findById(id);
		}
		
		@PostMapping("/becomeMentor/add")
		public String addBecomeMentorAppeal(@RequestBody BecomeMentorAppeal becomeMentorAppeal) {
			this.becomeMentorAppealService.addNewAppeal(becomeMentorAppeal);
			return "işlem başarılı";
		}
		
		@PostMapping("/becomeMentor/makeMenteeMentor")
		public String makeMenteeMentor(@RequestBody BecomeMentorAppeal becomeMentorAppeal) {
			this.becomeMentorAppealService.makeMenteeMentor(becomeMentorAppeal);
			return "işlem başarılı";
		}
		
		@GetMapping("/becomeMentor/delete/{appealId}")
		public String deleteBecomeMentorAppealByAppealId(@PathVariable("appealId") int appealId){
			return this.becomeMentorAppealService.deleteByAppealId(appealId);
		}
		////////////////////////////////////////////////////////////////////////////////
		
		
		
		@GetMapping("/mentorshipAppeal")
		public List<MentorshipAppeal>  getAllMentorshipAppeals(){
			return this.mentorshipAppealService.findAll();
		}
		
		@GetMapping("/mentorshipAppeal/get/{id}")
		public MentorshipAppeal getMentorshipAppealById(@PathVariable("id") int id){
			return this.mentorshipAppealService.findById(id);
		}
		
		@PostMapping("/mentorshipAppeal/add")
		public String addMentorshipAppeal(@RequestBody MentorshipAppeal mentorshipAppeal) {
			this.mentorshipAppealService.addNewAppeal(mentorshipAppeal);
			return "işlem başarılı";
		}

		@GetMapping("/mentorshipAppeal/delete/{appealId}")
		public String deleteMentorshipAppealByAppealId(@PathVariable("appealId") int appealId){
			return this.mentorshipAppealService.deleteByAppealId(appealId);
		}
		
		

		
}
