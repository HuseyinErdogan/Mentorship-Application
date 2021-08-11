package jsi.mentorship.business.abstracts;

import java.util.List;

import jsi.mentorship.models.concretes.MentorshipAppeal;

public interface MentorshipAppealService {
	List<MentorshipAppeal> findAll();
	MentorshipAppeal findById(int id);
	String addNewAppeal(MentorshipAppeal mentorshipAppeal);
	String deleteByAppealId(int appealId);
	String acceptAppeal(MentorshipAppeal mentorshipAppeal);
}
