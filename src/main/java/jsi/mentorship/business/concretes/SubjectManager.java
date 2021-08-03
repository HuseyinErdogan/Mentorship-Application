package jsi.mentorship.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jsi.mentorship.business.abstracts.SubjectService;
import jsi.mentorship.dataAccess.SubjectRepository;
import jsi.mentorship.models.concretes.Subject;

public class SubjectManager implements SubjectService{

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Override
	public List<Subject> findAll() {
		return this.subjectRepository.findAll();
	}

	@Override
	public Subject saveOrUpdateSubject(Subject subject) {
		return this.subjectRepository.save(subject);
	}

	@Override
	public Subject findBySubjectName(String subjectName) {
		return this.subjectRepository.findBySubjectName(subjectName);
	}

	@Override
	public String deleteSubjectBySubjectName(String subjectName) {
		this.subjectRepository.deleteSubjectBySubjectName(subjectName);
		return "işlem başarılı";
	}

}
