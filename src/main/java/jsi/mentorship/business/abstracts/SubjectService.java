package jsi.mentorship.business.abstracts;

import java.util.List;

import jsi.mentorship.core.utilities.results.DataResult;
import jsi.mentorship.core.utilities.results.Result;
import jsi.mentorship.models.concretes.Subject;
import jsi.mentorship.models.concretes.Subsubject;
import jsi.mentorship.models.wrappers.SubjectSubsubject;

public interface SubjectService {
	DataResult<List<Subject>> findAll();
	DataResult<List<Subsubject>> findAllSubsubjects();
	Result saveOrUpdateSubject(Subject subject);
	DataResult<Subject> findBySubjectName(String subjectName);
	Result deleteSubjectBySubjectName(String subjectName);
	Result addSubsubjectToSubject(SubjectSubsubject subjectSubsubject);
	Result deleteSubsubject(SubjectSubsubject subjectSubsubject);
	boolean subjectIncludesSubsubject(String subjectName, String subsubjectName);
	DataResult<Subject> findSubjectBySubsubjectName(String subsubjectName);
}
