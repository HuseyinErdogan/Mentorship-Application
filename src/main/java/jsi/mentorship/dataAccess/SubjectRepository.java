package jsi.mentorship.dataAccess;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import jsi.mentorship.models.concretes.Subject;

@Repository
public interface SubjectRepository extends MongoRepository<Subject, Integer>{
	Subject findBySubjectName(String subjectName);
	String deleteSubjectBySubjectName(String subjectName);

	
}
