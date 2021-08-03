package jsi.mentorship.models.concretes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
	
	@Id
	private int userId;
	
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String userPassword;
	
	private Role role;

}
