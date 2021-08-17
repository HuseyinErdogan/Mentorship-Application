package jsi.mentorship.dataAccess;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import jsi.mentorship.models.concretes.Mentorship;

@Repository
public interface MentorshipRepository extends MongoRepository<Mentorship, Integer>{
	List<Mentorship> findByMentorId(int mentorId);
	List<Mentorship> findByMenteeId(int menteeId);
	
	@Query("{'mentorId': ?0,'situation': 2}")
	List<Mentorship> findMentorsOldMentorships(int mentorId);
	
	@Query("{'mentorId': ?0,'situation':{$lt: 2}}")
	List<Mentorship> findMentorsActiveMentorships(int mentorId);
	
	@Query("{'menteeId': ?0,'situation':{$lt: 2}}")
	List<Mentorship> findMenteesActiveMentorships(int menteeId);
	
}
