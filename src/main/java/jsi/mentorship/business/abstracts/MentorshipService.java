package jsi.mentorship.business.abstracts;

import java.util.List;

import jsi.mentorship.models.concretes.Mentorship;
import jsi.mentorship.models.concretes.User;

public interface MentorshipService {
	List<Mentorship> findAll();
	Mentorship saveOrUpdateMentorship(Mentorship mentorship);
	List<Mentorship> findByMentorId(int mentorId);
	List<Mentorship> findByMenteeId(int menteeId);
	Mentorship findById(int id);
	void deleteMentorshipById(int id);
	User findMentorFromMentorshipById(int id);
	User findMenteeFromMentorshipById(int id);
}
