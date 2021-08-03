package jsi.mentorship.business.abstracts;

import java.util.List;

import jsi.mentorship.models.concretes.User;

public interface UserService {
	List<User> findAll();
	User saveOrUpdateUser(User user);
	User authenticate(String username, String userPassword);
	User findByUserId(int userId);
	void deleteUserById(int id);
}
