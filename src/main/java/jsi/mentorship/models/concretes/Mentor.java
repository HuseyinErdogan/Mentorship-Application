package jsi.mentorship.models.concretes;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Mentor extends Role{

	private List<Integer> mentorshipsGiven;
	private List<Subsubject> subsubjectsOfExpertise;
	private String description;
	
	public Mentor() {
		super("MENTOR");
		this.mentorshipsGiven = new ArrayList<Integer>();
		this.subsubjectsOfExpertise = new ArrayList<Subsubject>();
		this.description = "";
	}
	
}
