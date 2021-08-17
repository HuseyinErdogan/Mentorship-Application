package jsi.mentorship.business.abstracts;

import java.util.List;

import jsi.mentorship.core.utilities.results.DataResult;
import jsi.mentorship.core.utilities.results.Result;
import jsi.mentorship.models.concretes.BecomeMentorAppeal;

public interface BecomeMentorAppealService {
	DataResult<List<BecomeMentorAppeal>> findAll();
	DataResult<BecomeMentorAppeal> findById(int id);
	Result addNewAppeal(BecomeMentorAppeal becomeMentorAppeal);
	Result deleteByAppealId(int appealId);
	Result makeMenteeMentor(BecomeMentorAppeal becomeMentorAppeal);
}
