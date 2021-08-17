package jsi.mentorship.business.abstracts;

import java.util.List;

import jsi.mentorship.core.utilities.results.DataResult;
import jsi.mentorship.core.utilities.results.Result;
import jsi.mentorship.models.concretes.User;

public interface UserService {
	DataResult<List<User>> findAll();
	DataResult<User> saveOrUpdateUser(User user);
	DataResult<User> findByUserId(int userId);
	Result deleteUserById(int id);
	DataResult<User> findByUsername(String userName);
	boolean checkUsernameAndPassword(String username, String password);
	
	DataResult<List<User>> findMentors();
	
	DataResult<List<User>> findAllMentorsBySubsubject(String subsubjectName);
}
