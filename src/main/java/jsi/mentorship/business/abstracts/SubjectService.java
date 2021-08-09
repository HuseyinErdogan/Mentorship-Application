package jsi.mentorship.business.abstracts;

import java.util.List;

import jsi.mentorship.models.concretes.Subject;

public interface SubjectService {
	List<Subject> findAll();
	Subject saveOrUpdateSubject(Subject subject);
	Subject findBySubjectName(String subjectName);
	String deleteSubjectBySubjectName(String subjectName);
	Subject addSubsubjectToSubject(String subjectName, String subsubjectName);
}
