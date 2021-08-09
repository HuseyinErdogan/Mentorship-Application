package jsi.mentorship.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsi.mentorship.models.concretes.Subject;
import jsi.mentorship.models.concretes.Subsubject;
import jsi.mentorship.business.abstracts.SubjectService;
import jsi.mentorship.dataAccess.SubjectRepository;


@Service
public class SubjectManager implements SubjectService{

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private SequenceGenerator sequenceGenerator; 
	
	
	@Override
	public List<Subject> findAll() {
		return this.subjectRepository.findAll();
	}

	@Override
	public Subject saveOrUpdateSubject(Subject subject) {
		
		subject.setSubjectId(sequenceGenerator.getSequenceNumber(Subject.SEQUENCE_NAME));
		
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
	@Override
	public Subject addSubsubjectToSubject(String subjectName, String subsubjectName) {
		
		Subject subject = this.subjectRepository.findBySubjectName(subjectName);
		
		subject.getSubsubjects().add(new Subsubject(subsubjectName));
		
		this.subjectRepository.save(subject);
		
		return subject;
	}


}
