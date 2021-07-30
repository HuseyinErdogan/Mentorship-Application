package jsi.mentorship.models.concretes;

import java.util.ArrayList;
import java.util.List;

import jsi.mentorship.models.abstracts.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mentor implements Role{
	private int roleId;
	private List<Integer> mentorshipsGiven;
	private List<Subsubject> subsubjectsOfExpertise;
	private String description;
	
	public Mentor() {
		this.roleId = 2;
		this.mentorshipsGiven = new ArrayList<Integer>();
		this.subsubjectsOfExpertise = new ArrayList<Subsubject>();
		this.description = "";
		}
	
}
