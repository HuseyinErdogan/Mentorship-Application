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
@Document(collection = "become_mentor_appeals")
public class BecomeMentorAppeal {
	
	
	@Transient
	public static final String SEQUENCE_NAME = "become_mentor_appeal_sequence";
	
	@Id
	private int appealId;
	private User user;
	private String description;
	private List<Subsubject> subsubjects;
	
	public BecomeMentorAppeal() {
		this.subsubjects = new ArrayList<>();
	}

}
