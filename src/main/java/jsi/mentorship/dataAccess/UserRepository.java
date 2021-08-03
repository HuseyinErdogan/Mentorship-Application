package jsi.mentorship.dataAccess;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import jsi.mentorship.models.concretes.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer>{

	User findByUserId(int id);
}
