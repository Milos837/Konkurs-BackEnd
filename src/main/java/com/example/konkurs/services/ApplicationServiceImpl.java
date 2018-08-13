package com.example.konkurs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.konkurs.entities.ApplicationEntity;
import com.example.konkurs.entities.CertificateEntity;
import com.example.konkurs.entities.LanguageEntity;
import com.example.konkurs.entities.dto.ApplicationDto;
import com.example.konkurs.entities.dto.LanguageDto;
import com.example.konkurs.repositories.ApplicationRepository;
import com.example.konkurs.repositories.CertificateRepository;
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
	
	public ApplicationEntity save(Integer postingId, ApplicationDto newApplication) {
		ApplicationEntity application = new ApplicationEntity();
		application.setDeleted(false);
		application.setFirstName(newApplication.getFirstName());
		application.setLastName(newApplication.getLastName());
		application.setGender(newApplication.getGender());
		application.setEmail(newApplication.getEmail());
		application.setIdNumber(newApplication.getIdNumber());
		application.setSsn(newApplication.getSsn());
		application.setCitizenship(newApplication.getCitizenship());
		application.setSchool(newApplication.getSchool());
		application.setEducationLevel(newApplication.getEducationLevel());
		application.setPosting(postingRepository.findById(postingId).get());
		application = applicationRepository.save(application);
		
		for (String certification : newApplication.getCertifications()) {
			CertificateEntity cert = new CertificateEntity();
			cert.setCertificate(certification);
			cert.setDeleted(false);
			cert.setApplication(application);
			certificateRepository.save(cert);
		}
		
		for (LanguageDto language : newApplication.getLanguage()) {
			LanguageEntity lang = new LanguageEntity();
			lang.setLanguage(language.getLanguage());
			lang.setLevel(language.getLevel());
			lang.setDeleted(false);
			lang.setApplication(application);
			languageRepository.save(lang);
		}
		
		return application;
	}

}
