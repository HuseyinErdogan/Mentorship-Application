package jsi.mentorship.business.abstracts;

import java.util.List;

import jsi.mentorship.models.concretes.BecomeMentorAppeal;

public interface BecomeMentorAppealService {
	List<BecomeMentorAppeal> findAll();
	BecomeMentorAppeal findById(int id);
	String addNewAppeal(BecomeMentorAppeal becomeMentorAppeal);
	String deleteByAppealId(int appealId);
	String makeMenteeMentor(BecomeMentorAppeal becomeMentorAppeal);
}
