package jsi.mentorship.models.concretes;



import java.util.Stack;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jsi.mentorship.models.abstracts.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
	
	@Id
	private int id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	
	private Role role;
	
	private int mentorshipTakenId;
	
	//private Stack<Integer> oldMentorshipTaken;
	
	
	
	

}
