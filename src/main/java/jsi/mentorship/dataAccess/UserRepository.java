package jsi.mentorship.dataAccess;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import jsi.mentorship.models.concretes.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer>{

	User findByUserId(int id);
	User findByUsername(String userName);
	
	@Query("{'role.name': 'MENTOR'}")
	List<User> findMentors();
}
