package jsi.mentorship.business.abstracts;

import java.util.List;

import jsi.mentorship.models.concretes.Mentorship;

public interface MentorshipService {
	List<Mentorship> findAll();
	Mentorship saveOrUpdateMentorship(Mentorship mentorship);
	List<Mentorship> findByMentorId(int mentorId);
	List<Mentorship> findByMenteeId(int menteeId);
	void deleteMentorshipById(int id);
}
