package jsi.mentorship.models.concretes;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Phase {
	private int phaseNumber;
	private String phaseName;
	private String phaseDetails;
	private boolean isDone;
	private Date startingDate;
	private Date finishingDate;
	private String mentorComment;
	private int mentorAssesment;
	private String menteeComment;
	private int menteeAssesment;
	
	private boolean isMentorDone;
	private boolean isMenteeDone;
	
	public Phase() {
		this.isDone=false;
		this.isMenteeDone=false;
		this.isMentorDone=false;
	}
	
}
