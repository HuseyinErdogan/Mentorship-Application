package jsi.mentorship.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsi.mentorship.models.concretes.Subject;
import jsi.mentorship.models.concretes.Subsubject;
import jsi.mentorship.models.wrappers.SubjectSubsubject;
import jsi.mentorship.business.abstracts.SubjectService;
import jsi.mentorship.core.utilities.results.DataResult;
import jsi.mentorship.core.utilities.results.ErrorDataResult;
import jsi.mentorship.core.utilities.results.ErrorResult;
import jsi.mentorship.core.utilities.results.Result;
import jsi.mentorship.core.utilities.results.SuccessDataResult;
import jsi.mentorship.core.utilities.results.SuccessResult;
import jsi.mentorship.dataAccess.SubjectRepository;


@Service
public class SubjectManager implements SubjectService{

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private SequenceGenerator sequenceGenerator; 
	
	
	@Override
	public DataResult<List<Subject>> findAll() {
		return new SuccessDataResult<List<Subject>>(this.subjectRepository.findAll());
	}

	@Override
	public Result saveOrUpdateSubject(Subject subject) {
		
		if(subject.getSubjectName().equals(null) || subject.getSubjectName().equals(""))
			return new ErrorResult("Subject name field cannot be left blank");
		
		for (Subject sbj : subjectRepository.findAll()) {
			if(subject.getSubjectName().equalsIgnoreCase(sbj.getSubjectName()))
				return new ErrorResult("Subject is already exist");
		}
		
		subject.setSubjectId(sequenceGenerator.getSequenceNumber(Subject.SEQUENCE_NAME));
		this.subjectRepository.save(subject);
		return new SuccessResult("Subject is added");
	}

	@Override
	public DataResult<Subject> findBySubjectName(String subjectName) {
		return new SuccessDataResult<Subject>(this.subjectRepository.findBySubjectName(subjectName));
	}

	@Override
	public Result deleteSubjectBySubjectName(String subjectName) {
		this.subjectRepository.deleteSubjectBySubjectName(subjectName);
		return new SuccessResult("Subject is deleted");
	}
	@Override
	public Result addSubsubjectToSubject(SubjectSubsubject subjectSubsubject) {
		Subject subject = subjectSubsubject.getSubject();
		String subsubjectName = subjectSubsubject.getSubsubjectName();
		List<Subsubject> subsubjects = this.findAllSubsubjects().getData();
		
		for (Subsubject subsubject : subsubjects) {
			if(subsubjectName.equalsIgnoreCase(subsubject.getSubsubjectName()))
				return new ErrorResult("Subsubject is already exist");
		}
		
		if(subsubjectName.equals(null) || subsubjectName.equals(""))
			return new ErrorResult("Subsubject name field cannot be left blank");
		else if(subject.getSubjectName().equalsIgnoreCase(null) || subject.getSubjectName().equalsIgnoreCase(""))
			return new ErrorResult("Subject name field cannot be left blank");
		
		
		
		subject.getSubsubjects().add(new Subsubject(subsubjectName));
		this.subjectRepository.save(subject);
		return new SuccessResult("Subsubject is added to subject");
	}

	@Override
	public DataResult<List<Subsubject>> findAllSubsubjects() {
		List<Subsubject> subsubjects = new ArrayList<>();
		
		for (Subject subject : this.subjectRepository.findAll()) {
			for (Subsubject subsubject : subject.getSubsubjects()) {
				subsubjects.add(subsubject);
			}
		}
		return new SuccessDataResult<List<Subsubject>>(subsubjects);
	}

	@Override
	public Result deleteSubsubjectBySubsubjectName(String subsubjectName) {
		
		Subject subject = this.findSubjectBySubsubjectName(subsubjectName).getData();
		
		for (Subsubject subsubject : subject.getSubsubjects()) {
			if(subsubject.getSubsubjectName().equalsIgnoreCase(subsubjectName)) {
				subject.getSubsubjects().remove(subsubject);
				this.subjectRepository.save(subject);
				return new SuccessResult("Subsubject is successfully deleted");
			}
		}
		return new ErrorResult("Subsubject is not founded");
	}

	@Override
	public boolean subjectIncludesSubsubject(String subjectName, String subsubjectName) {
		for (Subsubject subsubject : this.subjectRepository.findBySubjectName(subjectName).getSubsubjects()) {
			if(subsubject.getSubsubjectName().equalsIgnoreCase(subsubjectName))
				return true;
		}
		return false;
	}

	@Override
	public DataResult<Subject> findSubjectBySubsubjectName(String subsubjectName) {
		for (Subject subject : this.subjectRepository.findAll()) {
			for (Subsubject subsubject : subject.getSubsubjects()) {
				if(subsubject.getSubsubjectName().equalsIgnoreCase(subsubjectName))
					return new SuccessDataResult<Subject>(subject);
			}
		}
		return new ErrorDataResult<Subject>();
	}


}
