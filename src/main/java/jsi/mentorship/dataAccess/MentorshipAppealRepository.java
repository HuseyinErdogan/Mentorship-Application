package jsi.mentorship.dataAccess;

import org.springframework.data.mongodb.repository.MongoRepository;

import jsi.mentorship.models.concretes.MentorshipAppeal;

public interface MentorshipAppealRepository extends MongoRepository<MentorshipAppeal, Integer>{
	String deleteByAppealId(int appealId);
	MentorshipAppeal findById(int id);
}
