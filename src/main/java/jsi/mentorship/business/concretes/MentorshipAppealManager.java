package jsi.mentorship.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsi.mentorship.business.abstracts.MentorshipAppealService;
import jsi.mentorship.business.abstracts.SubjectService;
import jsi.mentorship.core.utilities.results.DataResult;
import jsi.mentorship.core.utilities.results.ErrorResult;
import jsi.mentorship.core.utilities.results.Result;
import jsi.mentorship.core.utilities.results.SuccessDataResult;
import jsi.mentorship.core.utilities.results.SuccessResult;
import jsi.mentorship.dataAccess.MentorshipAppealRepository;
import jsi.mentorship.dataAccess.MentorshipRepository;
import jsi.mentorship.dataAccess.SubjectRepository;
import jsi.mentorship.models.concretes.BecomeMentorAppeal;
import jsi.mentorship.models.concretes.Mentorship;
import jsi.mentorship.models.concretes.MentorshipAppeal;
import jsi.mentorship.models.concretes.Subject;
import jsi.mentorship.models.concretes.Subsubject;

@Service
public class MentorshipAppealManager implements MentorshipAppealService{

	@Autowired
	private MentorshipAppealRepository mentorshipAppealRepository;
	
	@Autowired
	private MentorshipRepository mentorshipRepository;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private SequenceGenerator sequenceGenerator; 
	
	@Override
	public DataResult<List<MentorshipAppeal>> findAll() {
		return new SuccessDataResult<List<MentorshipAppeal>>(this.mentorshipAppealRepository.findAll(), "Appeals are successfully founded");
	}

	@Override
	public DataResult<MentorshipAppeal> findById(int id) {
		return new SuccessDataResult<MentorshipAppeal>(this.mentorshipAppealRepository.findById(id), "Appeal is successfully founded");
	}

	@Override
	public Result addNewAppeal(MentorshipAppeal mentorshipAppeal) {
		mentorshipAppeal.setAppealId(sequenceGenerator.getSequenceNumber(MentorshipAppeal.SEQUENCE_NAME));
		Subsubject subsubject = mentorshipAppeal.getSubsubject();
		List<Mentorship> mentorshipsActive= this.mentorshipRepository.findMenteesActiveMentorships(mentorshipAppeal.getMentee().getUserId());
		Subject subject = this.subjectService.findSubjectBySubsubjectName(subsubject.getSubsubjectName()).getData();
		System.out.println(mentorshipsActive.size()+" ===");
		for (Mentorship mentorship : mentorshipsActive) {
			System.out.println(mentorship.getSubsubject().getSubsubjectName()+"---");
			if(this.subjectService.subjectIncludesSubsubject(subject.getSubjectName(), mentorship.getSubsubject().getSubsubjectName()))
				return new ErrorResult("You have already mentorship under "+subject.getSubjectName()+" subject");
		}
		
		
		this.mentorshipAppealRepository.save(mentorshipAppeal);
		return new SuccessResult("Appeal is successfully added");
	}

	@Override
	public Result deleteByAppealId(int appealId) {
		this.mentorshipAppealRepository.deleteByAppealId(appealId);
		return new SuccessResult("Appeal is successfully deleted");
	}

	@Override
	public Result acceptMentorshipAppeal(int appealId) {
		 MentorshipAppeal mentorshipAppeal = this.mentorshipAppealRepository.findById(appealId);
		 System.out.println("===================== "+mentorshipAppeal.getMentor().getUserId());
		if(mentorshipRepository.findMenteesActiveMentorships(mentorshipAppeal.getMentor().getUserId()).size()>=2) {
			return new ErrorResult("There are currently 2 mentorship process");
		}
		
		Mentorship mentorship = new Mentorship();
		mentorship.setId(sequenceGenerator.getSequenceNumber(Mentorship.SEQUENCE_NAME));
		mentorship.setMentorId(mentorshipAppeal.getMentor().getUserId());
		mentorship.setMenteeId(mentorshipAppeal.getMentee().getUserId());
		mentorship.setSubsubject(mentorshipAppeal.getSubsubject());
		
		mentorshipRepository.save(mentorship);
		
		return new SuccessResult("Mentorship is successfully added");
	}

	@Override
	public DataResult<List<MentorshipAppeal>> findByMentorId(int mentorId) {
		return new SuccessDataResult<List<MentorshipAppeal>>(this.mentorshipAppealRepository.findByMentor(mentorId), "Appeals are successfully founded");
	}

	@Override
	public DataResult<List<MentorshipAppeal>> findByMenteeId(int menteeId) {
		return new SuccessDataResult<List<MentorshipAppeal>>(this.mentorshipAppealRepository.findByMentee(menteeId), "Appeals are successfully founded");
	}
	
}
