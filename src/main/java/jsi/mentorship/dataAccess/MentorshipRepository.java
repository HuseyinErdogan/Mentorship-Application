package jsi.mentorship.dataAccess;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import jsi.mentorship.models.concretes.Mentorship;

@Repository
public interface MentorshipRepository extends MongoRepository<Mentorship, Integer>{

}
