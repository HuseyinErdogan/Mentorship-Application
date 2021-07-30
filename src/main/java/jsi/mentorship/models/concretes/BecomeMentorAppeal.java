package jsi.mentorship.models.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Document(collection = "become_mentor_appeal")
public class BecomeMentorAppeal {
	private int mentorId;
	private String description;
	private List<Subsubject> subjects;
	
	public BecomeMentorAppeal() {
		this.subjects = new ArrayList<Subsubject>();
	}
}
