package jsi.mentorship.business.concretes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import jsi.mentorship.business.abstracts.MentorshipService;
import jsi.mentorship.core.utilities.results.DataResult;
import jsi.mentorship.core.utilities.results.Result;
import jsi.mentorship.core.utilities.results.SuccessDataResult;
import jsi.mentorship.core.utilities.results.SuccessResult;
import jsi.mentorship.dataAccess.MentorshipRepository;
import jsi.mentorship.dataAccess.UserRepository;
import jsi.mentorship.models.concretes.BecomeMentorAppeal;
import jsi.mentorship.models.concretes.Mentorship;
import jsi.mentorship.models.concretes.Phase;
import jsi.mentorship.models.concretes.User;
import jsi.mentorship.models.wrappers.MentorshipPhases;
@Service
public class MentorshipManager implements MentorshipService{
	
	@Autowired
	private MentorshipRepository mentorshipRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SequenceGenerator sequenceGenerator; 
	
	@Override
	public DataResult<List<Mentorship>> findAll() {
		return new SuccessDataResult<List<Mentorship>>(mentorshipRepository.findAll(), "Mentorships are successfully founded");
	}

	@Override
	public Result saveOrUpdateMentorship(Mentorship mentorship) {
		
		mentorship.setId(sequenceGenerator.getSequenceNumber(Mentorship.SEQUENCE_NAME));
		
		if(mentorship.getPhases().get(mentorship.getPhases().size()-1).isDone()) {
			mentorship.setSituation(2);;
		}
		
		
		this.mentorshipRepository.save(mentorship);
		return new SuccessResult("Mentorship is successfully added");
	}

	@Override
	public DataResult<List<Mentorship>> findByMentorId(int mentorId) {
		return new SuccessDataResult<List<Mentorship>>(mentorshipRepository.findByMentorId(mentorId), "Mentor is successfully founded");
	}

	@Override
	public DataResult<List<Mentorship>> findByMenteeId(int menteeId) {
		return new SuccessDataResult<List<Mentorship>>(mentorshipRepository.findByMenteeId(menteeId), "Mentee is successfully founded");
	}

	@Override
	public Result deleteMentorshipById(int id) {
		this.mentorshipRepository.deleteById(id);
		return new SuccessResult("Mentorship is successfully deleted");
	}

	@Override
	public DataResult<Mentorship> findById(int id) {
		return new SuccessDataResult<Mentorship>(this.mentorshipRepository.findById(id).get(), "Mentorship is succesfully founded");
	}
	
	@Override
	public DataResult<User> findMentorFromMentorshipById(int id) {
		Mentorship mentorship = mentorshipRepository.findById(id).get();
		return new SuccessDataResult<User>(this.userRepository.findByUserId(mentorship.getMentorId()), "Mentor is successfully founded");
	}

	@Override
	public DataResult<User> findMenteeFromMentorshipById(int id) {
		Mentorship mentorship = mentorshipRepository.findById(id).get();
		return new SuccessDataResult<User>(this.userRepository.findByUserId(mentorship.getMenteeId()), "Mentee is successfully founded");
	}

	@Override
	public Result addPhasesToMentorship(MentorshipPhases mentorshipPhases) {
		Mentorship mentorship = mentorshipPhases.getMentorship();
		List<Phase> phases = new ArrayList<>();
		
		Date tempDate = new Date();
		
		for (Phase phase : mentorshipPhases.getPhases()) {	
			phase.setStartingDate(tempDate);			
			tempDate = phase.getFinishingDate();	
			phases.add(phase);
		}

		mentorship.setPhases(phases);
		
		mentorship.setSituation(1);
		mentorshipRepository.save(mentorship);
		return new SuccessResult("Phases are successfully added");
	}

	@Override
	public DataResult<List<Mentorship>> findMentorsOldMentorships(int mentorId) {
		return new SuccessDataResult<>(this.mentorshipRepository.findMentorsOldMentorships(mentorId),"Mentorships are successfully founded");
	}

	@Override
	public DataResult<List<Mentorship>> findMentorsActiveMentorships(int mentorId) {
		return new SuccessDataResult<>(this.mentorshipRepository.findMentorsActiveMentorships(mentorId), "Active mentorships are successfully founded");
	}

	@Override
	public DataResult<List<Mentorship>> findMenteesActiveMentorships(int menteeId) {
		return new SuccessDataResult<>(this.mentorshipRepository.findMenteesActiveMentorships(menteeId), "Active mentorships are successfully founded");
	}

}
