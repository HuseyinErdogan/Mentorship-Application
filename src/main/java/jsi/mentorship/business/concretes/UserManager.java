package jsi.mentorship.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsi.mentorship.business.abstracts.UserService;
import jsi.mentorship.dataAccess.UserRepository;

import jsi.mentorship.models.concretes.User;

@Service
public class UserManager implements UserService{
	
	@Autowired
	private UserRepository userRepository;	

	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	@Override
	public User saveOrUpdateUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User authenticate(String username, String userPassword) {
		return null;
	}

	@Override
	public User findByUserId(int userId) {
		return this.userRepository.findByUserId(userId);
	}

	@Override
	public void deleteUserById(int id) {
		this.userRepository.deleteById(id);
	}

	@Override
	public User findByUsername(String userName) {
		return this.userRepository.findByUsername(userName);
	}

	@Override
	public boolean checkUsernameAndPassword(String username, String password) {
		
		User user = this.findByUsername(username);
		if(user!=null)
			if(user.getUserPassword().equals(password))
				return true;	
		return false;
	}

	
	


}
