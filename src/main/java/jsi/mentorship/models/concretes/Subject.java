package jsi.mentorship.models.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Document(collection = "subjects")
public class Subject {
	
	@Transient
	public static final String SEQUENCE_NAME = "subject_sequence";
	
	
	@Id
	private int subjectId;	
	private String subjectName;
	private List<Subsubject> subsubjects;
	
	public Subject() {
		this.subsubjects = new ArrayList<Subsubject>();
	}
	
}
