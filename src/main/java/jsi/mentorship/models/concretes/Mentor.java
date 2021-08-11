package jsi.mentorship.models.concretes;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mentor extends Role{

	private List<Subsubject> subsubjectsOfExpertise;
	private String description;
	
	public Mentor() {
		super("MENTOR");
		this.subsubjectsOfExpertise = new ArrayList<Subsubject>();
		this.description = "";
	}
	
}
