package jsi.mentorship.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsi.mentorship.business.abstracts.BecomeMentorAppealService;
import jsi.mentorship.dataAccess.BecomeMentorAppealRepository;
import jsi.mentorship.dataAccess.UserRepository;
import jsi.mentorship.models.concretes.BecomeMentorAppeal;
import jsi.mentorship.models.concretes.Mentor;
import jsi.mentorship.models.concretes.User;

@Service
public class BecomeMentorAppealManager implements BecomeMentorAppealService{
	
	@Autowired
	private BecomeMentorAppealRepository becomeMentorAppealRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SequenceGenerator sequenceGenerator; 
	
	@Override
	public List<BecomeMentorAppeal> findAll() {
		return this.becomeMentorAppealRepository.findAll();
	}

	@Override
	public String addNewAppeal(BecomeMentorAppeal becomeMentorAppeal) {
		becomeMentorAppeal.setAppealId(sequenceGenerator.getSequenceNumber(BecomeMentorAppeal.SEQUENCE_NAME));
		this.becomeMentorAppealRepository.save(becomeMentorAppeal);
		return "İşlem Başarılı";
	}

	@Override
	public String deleteByAppealId(int appealId) {
		this.becomeMentorAppealRepository.deleteByAppealId(appealId);
		return "İşlem Başarılı";
	}

	@Override
	public BecomeMentorAppeal findById(int id) {
		return this.becomeMentorAppealRepository.findById(id);
	}

	@Override
	public String makeMenteeMentor(BecomeMentorAppeal becomeMentorAppeal) {
		User user = this.userRepository.findByUserId(becomeMentorAppeal.getUser().getUserId());
		
		user.setRole(new Mentor(becomeMentorAppeal.getSubsubjects(), becomeMentorAppeal.getDescription()));
		user.getRole().setName("MENTOR");
		this.userRepository.save(user);
		
		return user.getUsername()+ " mentör olarak atandı.";
	}

}
