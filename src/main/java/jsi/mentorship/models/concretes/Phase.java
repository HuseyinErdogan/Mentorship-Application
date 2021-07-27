package jsi.mentorship.models.concretes;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Phase {
	private int phaseNumber;
	private String name;
	private boolean isDone;
	private Date startingDate;
	private Date finishingDate;
	private String mentorComment;
	private int mentorAssesment;
	private String menteeComment;
	private int menteeAssesment;
	
	public Phase() {
		this.isDone=false;
	}
	
}
