package jsi.mentorship.models.wrappers;




import jsi.mentorship.models.concretes.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectSubsubject {
	private Subject subject;
	private String subsubjectName;

}
