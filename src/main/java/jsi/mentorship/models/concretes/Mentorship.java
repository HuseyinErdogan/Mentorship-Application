package jsi.mentorship.models.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@Document(collection = "mentorships")
public class Mentorship {
	
	@Id
	private int id;
	
	private int mentorId;
	private int menteeId;
	private List<Phase> phases;
	private int situation; // 0- faz oluşturulmadı, 1- fazlar oluşturuldu, 2- tamamlandı
	private Subsubject subsubject;
	
	
	public Mentorship() {
		this.phases = new ArrayList<>();
	}
}
