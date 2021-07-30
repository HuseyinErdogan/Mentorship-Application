package jsi.mentorship.models.concretes;

import jsi.mentorship.models.abstracts.Role;
import lombok.Data;

@Data
public class Mentee implements Role{
	private int roleId;
	
	public Mentee() {
		roleId=1;
	}
}
