package jsi.mentorship.models.wrappers;

import java.util.List;

import jsi.mentorship.models.concretes.Mentorship;
import jsi.mentorship.models.concretes.Phase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorshipPhases {
	private Mentorship mentorship;
	private List<Phase> phases;

}
