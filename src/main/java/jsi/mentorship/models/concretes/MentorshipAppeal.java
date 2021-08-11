package jsi.mentorship.models.concretes;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "mentorship_appeals")
public class MentorshipAppeal {
	
	@Transient
	public static final String SEQUENCE_NAME = "mentorship_appeal_sequence";
	
	
	@Id
	private int appealId;
	
	private int mentorId;
	private int menteeId;
}
