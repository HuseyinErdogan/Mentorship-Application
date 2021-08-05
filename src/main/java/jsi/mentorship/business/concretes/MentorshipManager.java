package jsi.mentorship.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsi.mentorship.business.abstracts.MentorshipService;
import jsi.mentorship.dataAccess.MentorshipRepository;
import jsi.mentorship.models.concretes.Mentorship;

@Service
public class MentorshipManager implements MentorshipService{

	@Autowired
	private MentorshipRepository mentorshipRepository;
	
	@Override
	public List<Mentorship> findAll() {
		return this.mentorshipRepository.findAll();
	}

	@Override
	public Mentorship saveOrUpdateMentorship(Mentorship mentorship) {
		return this.mentorshipRepository.save(mentorship);
	}

	@Override
	public List<Mentorship> findByMentorId(int mentorId) {
		return this.mentorshipRepository.findByMentorId(mentorId);
	}

	@Override
	public List<Mentorship> findByMenteeId(int menteeId) {
		return this.mentorshipRepository.findByMenteeId(menteeId);
	}

	@Override
	public void deleteMentorshipById(int id) {
		this.mentorshipRepository.deleteById(id);;	
	}

}
