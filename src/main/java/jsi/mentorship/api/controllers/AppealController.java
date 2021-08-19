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

import jsi.mentorship.business.abstracts.BecomeMentorAppealService;
import jsi.mentorship.business.abstracts.MentorshipAppealService;
import jsi.mentorship.core.utilities.results.DataResult;
import jsi.mentorship.core.utilities.results.Result;
import jsi.mentorship.models.concretes.BecomeMentorAppeal;
import jsi.mentorship.models.concretes.MentorshipAppeal;

@Configuration
@CrossOrigin()
@RestController
@RequestMapping("/api/appeals")
public class AppealController {	

		@Autowired
		private BecomeMentorAppealService becomeMentorAppealService;
		@Autowired
		private MentorshipAppealService mentorshipAppealService;
		

		@GetMapping("/becomeMentor")
		public DataResult<List<BecomeMentorAppeal>>  getAllBecomeMentorAppeals(){
			return this.becomeMentorAppealService.findAll();
		}
		
		@GetMapping("/becomeMentor/get/{id}")
		public DataResult<BecomeMentorAppeal> getBecomeMentorAppealById(@PathVariable("id") int id){
			return this.becomeMentorAppealService.findById(id);
		}
		
		@PostMapping("/becomeMentor/add")
		public Result addBecomeMentorAppeal(@RequestBody BecomeMentorAppeal becomeMentorAppeal) {
			return this.becomeMentorAppealService.addNewAppeal(becomeMentorAppeal);
		}
		
		@PostMapping("/becomeMentor/makeMenteeMentor")
		public Result makeMenteeMentor(@RequestBody BecomeMentorAppeal becomeMentorAppeal) {
			return this.becomeMentorAppealService.makeMenteeMentor(becomeMentorAppeal);
			
		}
		
		@GetMapping("/becomeMentor/delete/{appealId}")
		public Result deleteBecomeMentorAppealByAppealId(@PathVariable("appealId") int appealId){
			return this.becomeMentorAppealService.deleteByAppealId(appealId);
		}
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		@GetMapping("/mentorshipAppeals")
		public DataResult<List<MentorshipAppeal>>  getAllMentorshipAppeals(){
			return this.mentorshipAppealService.findAll();
		}
		
		@GetMapping("/mentorshipAppeals/get/{id}")
		public DataResult<MentorshipAppeal> getMentorshipAppealById(@PathVariable("id") int id){
			return this.mentorshipAppealService.findById(id);
		}
		
		@PostMapping("/mentorshipAppeals/add")
		public Result addMentorshipAppeal(@RequestBody MentorshipAppeal mentorshipAppeal) {
			return this.mentorshipAppealService.addNewAppeal(mentorshipAppeal);
		}

		@DeleteMapping("/mentorshipAppeals/delete/{appealId}")
		public Result deleteMentorshipAppealByAppealId(@PathVariable("appealId") int appealId){
			return this.mentorshipAppealService.deleteByAppealId(appealId);
		}
		
		@GetMapping("/mentorshipAppeals/getByMentor/{mentorId}")
		public DataResult<List<MentorshipAppeal>> getMentorshipAppealsByMentorId(@PathVariable("mentorId") int mentorId){
			return this.mentorshipAppealService.findByMentorId(mentorId);
		}
		
		@GetMapping("/mentorshipAppeals/getByMentee/{menteeId}")
		public DataResult<List<MentorshipAppeal>> getMentorshipAppealsByMenteeId(@PathVariable("menteeId") int menteeId){
			return this.mentorshipAppealService.findByMentorId(menteeId);
		}
		
		@GetMapping("/mentorshipAppeals/acceptAppeal/{appealId}")
		public Result acceptMentorshipAppeal(@PathVariable("appealId") int appealId) {
			return this.mentorshipAppealService.acceptMentorshipAppeal(appealId);
		}
		
		

		
}
