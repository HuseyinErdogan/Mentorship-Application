package jsi.mentorship.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsi.mentorship.business.abstracts.MentorshipAppealService;
import jsi.mentorship.dataAccess.MentorshipAppealRepository;
import jsi.mentorship.models.concretes.BecomeMentorAppeal;
import jsi.mentorship.models.concretes.MentorshipAppeal;

@Service
public class MentorshipAppealManager implements MentorshipAppealService{

	@Autowired
	private MentorshipAppealRepository mentorshipAppealRepository;
	
	@Autowired
	private SequenceGenerator sequenceGenerator; 
	
	@Override
	public List<MentorshipAppeal> findAll() {
		return this.mentorshipAppealRepository.findAll();
	}

	@Override
	public MentorshipAppeal findById(int id) {
		return this.mentorshipAppealRepository.findById(id);
	}

	@Override
	public String addNewAppeal(MentorshipAppeal mentorshipAppeal) {
		mentorshipAppeal.setAppealId(sequenceGenerator.getSequenceNumber(BecomeMentorAppeal.SEQUENCE_NAME));
		this.mentorshipAppealRepository.save(mentorshipAppeal);
		return "İşlem başarılı";
	}

	@Override
	public String deleteByAppealId(int appealId) {
		return this.mentorshipAppealRepository.deleteByAppealId(appealId);
	}

	@Override
	public String acceptAppeal(MentorshipAppeal MentorshipAppeal) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
