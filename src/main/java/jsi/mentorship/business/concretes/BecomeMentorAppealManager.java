package jsi.mentorship.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsi.mentorship.business.abstracts.BecomeMentorAppealService;
import jsi.mentorship.core.utilities.results.DataResult;
import jsi.mentorship.core.utilities.results.ErrorResult;
import jsi.mentorship.core.utilities.results.Result;
import jsi.mentorship.core.utilities.results.SuccessDataResult;
import jsi.mentorship.core.utilities.results.SuccessResult;
import jsi.mentorship.dataAccess.BecomeMentorAppealRepository;
import jsi.mentorship.dataAccess.UserRepository;
import jsi.mentorship.models.concretes.BecomeMentorAppeal;
import jsi.mentorship.models.concretes.Role;
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
	public DataResult<List<BecomeMentorAppeal>> findAll() {
		
		return new SuccessDataResult<List<BecomeMentorAppeal>>(this.becomeMentorAppealRepository.findAll(),"Appeals successfully listed");
	}

	@Override
	public Result addNewAppeal(BecomeMentorAppeal becomeMentorAppeal) {

		if(becomeMentorAppeal.getSubsubjects().size()==0)
			return new ErrorResult("Subsubject is not selected");
		else if(becomeMentorAppeal.getDescription().equals(null) || becomeMentorAppeal.getDescription() == "") 
			return new ErrorResult("Description field cannot be left blank");
		
		int count=0;
		for (BecomeMentorAppeal appeal : becomeMentorAppealRepository.findAll()) {
			if(appeal.getUser().getUserId()==becomeMentorAppeal.getUser().getUserId())
				count++;
		}
		if(count>=3)
			return new ErrorResult("You have already 3 appeal");
		
		becomeMentorAppeal.setAppealId(sequenceGenerator.getSequenceNumber(BecomeMentorAppeal.SEQUENCE_NAME));
		this.becomeMentorAppealRepository.save(becomeMentorAppeal);
		return new SuccessResult("New appeal successfully added");
	}

	@Override
	public Result deleteByAppealId(int appealId) {
		this.becomeMentorAppealRepository.deleteByAppealId(appealId);
		return new SuccessResult("Appeal is deleted.");
	}

	@Override
	public DataResult<BecomeMentorAppeal> findById(int id) {
		return new SuccessDataResult<BecomeMentorAppeal>(becomeMentorAppealRepository.findById(id), "The appeal is founded");
	}

	@Override
	public Result makeMenteeMentor(BecomeMentorAppeal becomeMentorAppeal) {
		User user = this.userRepository.findByUserId(becomeMentorAppeal.getUser().getUserId());
			
		user.setRole(new Role(Role.ROLE_ADMIN, becomeMentorAppeal.getSubsubjects(), becomeMentorAppeal.getDescription()));
		this.userRepository.save(user);
		
		return new SuccessResult(user.getUsername()+ "");
	}

}
