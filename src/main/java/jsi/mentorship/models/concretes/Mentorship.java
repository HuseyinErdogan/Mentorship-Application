package jsi.mentorship.models.concretes;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mentorship {
	private int id;
	private int mentorId;
	private int menteeId;
	private List<Phase> phases;
	private int situation; // 0- faz oluşturulmadı, 1- fazlar oluşturuldu, 3- tamamlandı
}
