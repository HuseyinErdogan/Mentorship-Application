package jsi.mentorship.models.concretes;

import java.util.List;

import jsi.mentorship.models.abstracts.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mentor implements Role{
	private int roleId;
	private List<Mentorship> mentorshipsGiven;
	private List<Subject> subjectsGiven;
	
	
	public Mentor() {
		roleId = 2;
	}
	
	public void getAllMentorshipApplies() {
		System.out.println("Get All Mentorship Applies");
	}
	
	public void acceptOrRejectMentorshipApply(boolean flag, Mentorship mentorship) {
		System.out.println("Accept or reject ");
		
	}
}
