package jsi.mentorship.business.abstracts;

import java.util.List;

import jsi.mentorship.core.utilities.results.DataResult;
import jsi.mentorship.core.utilities.results.Result;
import jsi.mentorship.models.concretes.Mentorship;
import jsi.mentorship.models.concretes.User;
import jsi.mentorship.models.wrappers.MentorshipPhases;

public interface MentorshipService {
	DataResult<List<Mentorship>> findAll();
	Result saveOrUpdateMentorship(Mentorship mentorship);
	DataResult<List<Mentorship>> findByMentorId(int mentorId);
	DataResult<List<Mentorship>> findByMenteeId(int menteeId);
	DataResult<Mentorship> findById(int id);
	Result deleteMentorshipById(int id);
	DataResult<User> findMentorFromMentorshipById(int id);
	DataResult<User> findMenteeFromMentorshipById(int id);
	Result addPhasesToMentorship(MentorshipPhases mentorshipPhases);
	DataResult<List<Mentorship>> findMentorsOldMentorships(int mentorId);
	DataResult<List<Mentorship>> findMentorsActiveMentorships(int mentorId);
	DataResult<List<Mentorship>> findMenteesActiveMentorships(int menteeId);
}
