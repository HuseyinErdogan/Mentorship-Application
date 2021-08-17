package jsi.mentorship.dataAccess;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import jsi.mentorship.models.concretes.MentorshipAppeal;

public interface MentorshipAppealRepository extends MongoRepository<MentorshipAppeal, Integer>{
	String deleteByAppealId(int appealId);
	MentorshipAppeal findById(int id);
	
	@Query("{'mentor.userId': ?0}")
	List<MentorshipAppeal> findByMentor(int mentorId);
	
	@Query("{'mentee.userId': ?0}")
	List<MentorshipAppeal> findByMentee(int menteeId);
}
