package com.example.konkurs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.konkurs.entities.ApplicationEntity;
import com.example.konkurs.entities.CandidateEntity;
import com.example.konkurs.entities.CandidateLanguageEntity;
import com.example.konkurs.entities.CertificateEntity;
import com.example.konkurs.entities.CitizenshipEntity;
import com.example.konkurs.entities.EducationEntity;
import com.example.konkurs.entities.LanguageEntity;
import com.example.konkurs.entities.dto.ApplicationDto;
import com.example.konkurs.entities.dto.LanguageDto;
import com.example.konkurs.repositories.ApplicationRepository;
import com.example.konkurs.repositories.CandidateLanguageRepository;
import com.example.konkurs.repositories.CandidateRepository;
import com.example.konkurs.repositories.CertificateRepository;
import com.example.konkurs.repositories.CitizenshipRepository;
import com.example.konkurs.repositories.EducationRepository;
import com.example.konkurs.repositories.LanguageRepository;
import com.example.konkurs.repositories.PostingRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService{
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private CertificateRepository certificateRepository;
	
	@Autowired
	private PostingRepository postingRepository;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private CandidateLanguageRepository candidateLanguageRepository;
	
	@Autowired
	private EducationRepository educationRepository;
	
	@Autowired
	private CitizenshipRepository citizenshipRepository;
	
	public ApplicationEntity save(Integer postingId, ApplicationDto newApplication) {
		ApplicationEntity application = new ApplicationEntity();
		CandidateEntity candidate = new CandidateEntity();
		
		candidate.setDeleted(false);
		candidate.setFirstName(newApplication.getFirstName());
		candidate.setFirstName(newApplication.getFirstName());
		candidate.setLastName(newApplication.getLastName());
		candidate.setGender(newApplication.getGender());
		candidate.setEmail(newApplication.getEmail());
		candidate.setIdNumber(newApplication.getIdNumber());
		candidate.setSsn(newApplication.getSsn());
		
		CitizenshipEntity citizenship = citizenshipRepository.findById(newApplication.getCitizenshipId()).get();
		
		candidate.setCitizenship(citizenship);
		candidate.setEducationLevel(newApplication.getEducationLevel());
		candidate.setNote(newApplication.getCandidateNote());
		candidate = candidateRepository.save(candidate);
		
		application.setDeleted(false);
		application.setCandidate(candidate);
		application.setNote(newApplication.getApplicationNote());
		application.setPosting(postingRepository.findById(postingId).get());
		application = applicationRepository.save(application);
		
		for (CertificateEntity certification : newApplication.getCertifications()) {
			CertificateEntity cert = new CertificateEntity();
			cert.setCertificate(certification.getCertificate());
			cert.setNote(certification.getNote());
			cert.setDeleted(false);
			cert.setApplication(application);
			certificateRepository.save(cert);
		}
		
		for (LanguageDto language : newApplication.getLanguage()) {
			LanguageEntity lang = languageRepository.findById(language.getLanguageId()).get();
			
			CandidateLanguageEntity canLang = new CandidateLanguageEntity();
			canLang.setDeleted(false);
			canLang.setCandidate(candidate);
			canLang.setLanguage(lang);
			canLang.setNote(language.getNote());
			candidateLanguageRepository.save(canLang);
		}
		
		for (EducationEntity education : newApplication.getEducation()) {
			EducationEntity edu = new EducationEntity();
			edu.setDeleted(false);
			edu.setSchoolName(education.getSchoolName());
			edu.setCandidate(candidate);
			edu.setNote(education.getNote());
			educationRepository.save(edu);
		}
		
		return application;
	}
	
	public ApplicationEntity delete(Integer applicationId) {
		if (applicationRepository.existsById(applicationId) && !applicationRepository.findById(applicationId).get().getDeleted()) {
			ApplicationEntity application = applicationRepository.findById(applicationId).get();
			application.setDeleted(true);
			applicationRepository.save(application);
			return application;
		}
		return null;
	}

}
