package jsi.mentorship.models.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Document(collection = "subjects")
public class Subject {
	@Id
	private int subjectId;
	
	private String subjectName;
	private List<Subsubject> subsubjects;
	
	public Subject() {
		this.subsubjects = new ArrayList<Subsubject>();
	}
	
}
