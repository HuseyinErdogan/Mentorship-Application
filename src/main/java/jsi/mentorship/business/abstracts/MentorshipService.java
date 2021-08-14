package jsi.mentorship.business.abstracts;

import java.util.List;

import jsi.mentorship.models.concretes.Mentorship;
import jsi.mentorship.models.concretes.User;
import jsi.mentorship.models.wrappers.MentorshipPhases;

public interface MentorshipService {
	List<Mentorship> findAll();
	Mentorship saveOrUpdateMentorship(Mentorship mentorship);
	List<Mentorship> findByMentorId(int mentorId);
	List<Mentorship> findByMenteeId(int menteeId);
	Mentorship findById(int id);
	void deleteMentorshipById(int id);
	User findMentorFromMentorshipById(int id);
	User findMenteeFromMentorshipById(int id);
	String addPhasesToMentorship(MentorshipPhases mentorshipPhases);
}
