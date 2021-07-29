package jsi.mentorship.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsi.mentorship.dataAccess.MentorshipRepository;

@RestController
@Configuration
@RequestMapping("/administrator")
public class AdministratorController {
	
	@Autowired
	private MentorshipRepository mentorshipRepository;
	

}
