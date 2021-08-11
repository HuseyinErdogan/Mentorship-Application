package jsi.mentorship.dataAccess;

import org.springframework.data.mongodb.repository.MongoRepository;

import jsi.mentorship.models.concretes.BecomeMentorAppeal;


public interface BecomeMentorAppealRepository extends MongoRepository<BecomeMentorAppeal, Integer>{
	String deleteByAppealId(int appealId);
	BecomeMentorAppeal findById(int id);
}
