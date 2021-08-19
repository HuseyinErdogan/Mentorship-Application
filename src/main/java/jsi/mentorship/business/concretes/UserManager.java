package jsi.mentorship.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import jsi.mentorship.business.abstracts.UserService;
import jsi.mentorship.core.utilities.results.DataResult;
import jsi.mentorship.core.utilities.results.Result;
import jsi.mentorship.core.utilities.results.SuccessDataResult;
import jsi.mentorship.core.utilities.results.SuccessResult;
import jsi.mentorship.dataAccess.UserRepository;
import jsi.mentorship.models.concretes.Subsubject;
import jsi.mentorship.models.concretes.User;

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Service
public class UserManager implements UserService{
	
	@Autowired
	private UserRepository userRepository;	

	@Override
	public DataResult<List<User>> findAll() {
		return new SuccessDataResult<List<User>>(this.userRepository.findAll(), "Users are successfully founded");
	}

	@Override
	public DataResult<User> saveOrUpdateUser(User user) {
		return new SuccessDataResult<User>(this.userRepository.save(user), "User is successfully saved");
	}


	@Override
	public DataResult<User> findByUserId(int userId) {
		return new SuccessDataResult<User>(this.userRepository.findByUserId(userId), "User is successfully founded");
	}

	@Override
	public Result deleteUserById(int id) {
		this.userRepository.deleteById(id);
		return new SuccessResult("User is successfully deleted");
	}

	@Override
	public DataResult<User> findByUsername(String userName) {
		return new SuccessDataResult<User>(this.userRepository.findByUsername(userName));
	}

	@Override
	public boolean checkUsernameAndPassword(String username, String password) {
		
		User user = this.findByUsername(username).getData();
		if(user!=null)
			if(user.getUserPassword().equals(password))
				return true;	
		return false;
	}

	@Override
	public DataResult<List<User>> findMentors() {
		return new SuccessDataResult<List<User>>(this.userRepository.findMentors(),"Mentors are succesfully founded");
	}

	@Override
	public DataResult<List<User>> findAllMentorsBySubsubject(String subsubjectName) {
		
		List<User> mentors = this.userRepository.findMentors();
		List<User> result = new ArrayList<User>();
		for (User user : mentors) {
			if(user.getRole().getSubsubjectsOfExpertise().contains(new Subsubject(subsubjectName)))
				result.add(user);
		}
		
		return new SuccessDataResult<List<User>>(result, "Mentors are successfully founded");
	}

	@Override
	public DataResult<User> findByEmail(String email) {
		return new SuccessDataResult<User>(this.userRepository.findByEmail(email),"User is successfully founded");
	}

	@Override
	public DataResult<List<User>> findMentorsByDescription(String description) {
		return new SuccessDataResult<List<User>>(this.userRepository.findMentorsByDescription(description));
	}


}
