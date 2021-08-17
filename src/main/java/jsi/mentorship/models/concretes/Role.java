package jsi.mentorship.models.concretes;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role {
	
	public static final String ROLE_ADMIN ="ADMIN";
	public static final String ROLE_MENTOR ="MENTOR";
	public static final String ROLE_MENTEE ="MENTEE";
	
	
	private String name;
	private List<Subsubject> subsubjectsOfExpertise;
	private String description;
	
	public Role(String name) {
		this.name=name;
		this.subsubjectsOfExpertise= new ArrayList<Subsubject>();
		this.description="";
	}
	
}
