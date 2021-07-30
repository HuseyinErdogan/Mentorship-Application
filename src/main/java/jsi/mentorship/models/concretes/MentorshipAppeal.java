package jsi.mentorship.models.concretes;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Document(collection = "mentorship_appeal")
public class MentorshipAppeal {
	private int mentorId;
	private int menteeId;
	private boolean mentorApproval;
	
	public MentorshipAppeal() {
		this.mentorApproval = false;
	}
}
