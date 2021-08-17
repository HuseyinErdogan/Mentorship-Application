package jsi.mentorship.business.abstracts;

import java.util.List;

import jsi.mentorship.core.utilities.results.DataResult;
import jsi.mentorship.core.utilities.results.Result;
import jsi.mentorship.models.concretes.MentorshipAppeal;

public interface MentorshipAppealService {
	DataResult<List<MentorshipAppeal>> findAll();
	DataResult<MentorshipAppeal> findById(int id);
	Result addNewAppeal(MentorshipAppeal mentorshipAppeal);
	Result deleteByAppealId(int appealId);
	Result acceptMentorshipAppeal(int appealId);
	DataResult<List<MentorshipAppeal>> findByMentorId(int mentorId);
	DataResult<List<MentorshipAppeal>> findByMenteeId(int menteeId);
}
