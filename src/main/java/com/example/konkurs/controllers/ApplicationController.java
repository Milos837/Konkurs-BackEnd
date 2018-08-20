package com.example.konkurs.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.konkurs.entities.ApplicationEntity;
import com.example.konkurs.entities.CandidateLanguageEntity;
import com.example.konkurs.entities.CertificateEntity;
import com.example.konkurs.entities.CitizenshipEntity;
import com.example.konkurs.entities.CvEntity;
import com.example.konkurs.entities.EducationEntity;
import com.example.konkurs.entities.LanguageEntity;
import com.example.konkurs.entities.PostingEntity;
import com.example.konkurs.entities.dto.ApplicationDto;
import com.example.konkurs.repositories.ApplicationRepository;
import com.example.konkurs.repositories.CandidateLanguageRepository;
import com.example.konkurs.repositories.CertificateRepository;
import com.example.konkurs.repositories.CitizenshipRepository;
import com.example.konkurs.repositories.CvRepository;
import com.example.konkurs.repositories.EducationRepository;
import com.example.konkurs.repositories.LanguageRepository;
import com.example.konkurs.repositories.PostingRepository;
import com.example.konkurs.services.ApplicationService;
import com.example.konkurs.services.FileHandler;

@RestController
@RequestMapping(value = "/api/v1/applications")
public class ApplicationController {
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private PostingRepository postingRepository;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private CitizenshipRepository citizenshipRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private CandidateLanguageRepository candidateLanguageRepository;
	
	@Autowired
	private EducationRepository educationRepository;
	
	@Autowired
	private CertificateRepository certificateRepository;
	
	@Autowired
	private FileHandler fileHandler;
	
	@Autowired
	private CvRepository cvRepository;
	
	//	Vrati sve
	@GetMapping("/")
	public ResponseEntity<?> getAll() {
		
		List<ApplicationEntity> applications = ((List<ApplicationEntity>) applicationRepository.findAll())
				.stream().filter(app -> !app.getDeleted().equals(true))
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<ApplicationEntity>>(applications, HttpStatus.OK);
	}
	
	//	Vrati po ID-u
	@GetMapping("/{applicationId}")
	public ResponseEntity<?> getOne(@PathVariable Integer applicationId) {
		if (applicationRepository.existsById(applicationId) && !applicationRepository.findById(applicationId).get().getDeleted()) {
			ApplicationEntity application = applicationRepository.findById(applicationId).get();
			return new ResponseEntity<ApplicationEntity>(application, HttpStatus.OK);
		}
		return null;
	}
	
	//	Dodaj novi
	@PostMapping("/postings/{postingId}/no-security/")
	public ResponseEntity<?> save(@PathVariable Integer postingId, @RequestBody ApplicationDto newApplication) {
		if(postingRepository.existsById(postingId)) {
			ApplicationEntity application = applicationService.save(postingId, newApplication);
			return new ResponseEntity<ApplicationEntity>(application, HttpStatus.OK);
		}
		return null;
	}
	
	//	Obrisi (logicki)
	@DeleteMapping("/{applicationId}")
	public ResponseEntity<?> delete(@PathVariable Integer applicationId) {
		ApplicationEntity application = applicationService.delete(applicationId);
		if (application != null) {
			return new ResponseEntity<ApplicationEntity>(application, HttpStatus.OK);
		}
		return null;
	}
	
	//	Vrati sva drzavljanstva
	@GetMapping("/citizenships/no-security/")
	public ResponseEntity<?> getAllCitizenships() {
		List<CitizenshipEntity> citizenships = ((List<CitizenshipEntity>) citizenshipRepository.findAll())
				.stream().filter(citizenship -> !citizenship.getDeleted().equals(true))
				.collect(Collectors.toList());
		return new ResponseEntity<List<CitizenshipEntity>>(citizenships, HttpStatus.OK);
	}
	
	//	Vrati sve jezike
	@GetMapping("/languages/no-security/")
	public ResponseEntity<?> getAllLanguages() {
		List<LanguageEntity> languages = ((List<LanguageEntity>) languageRepository.findAll())
				.stream().filter(lang -> !lang.getDeleted().equals(true))
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<LanguageEntity>>(languages, HttpStatus.OK);
	}
	
	//	vrati prijave za konkurs
	@GetMapping("/posting/{postingId}")
	public ResponseEntity<?> getApplicationsForPosting(@PathVariable Integer postingId) {
		if (postingRepository.existsById(postingId) && !postingRepository.findById(postingId).get().getDeleted()) {
			PostingEntity posting = postingRepository.findById(postingId).get();		
			List<ApplicationEntity> applications = ((List<ApplicationEntity>) applicationRepository.findByPosting(posting))
					.stream().filter(app -> !app.getDeleted().equals(true))
					.collect(Collectors.toList());
			return new ResponseEntity<List<ApplicationEntity>>(applications, HttpStatus.OK);
		}
		return null;
	}
	
	//	Vrati jezike za aplikaciju
	@GetMapping("/{appId}/languages/")
	public ResponseEntity<?> getLanguagesForApplication(@PathVariable Integer appId) {
		if (applicationRepository.existsById(appId) && !applicationRepository.findById(appId).get().getDeleted()) {
			ApplicationEntity application = applicationRepository.findById(appId).get();
			List<LanguageEntity> languages = ((List<CandidateLanguageEntity>) candidateLanguageRepository
					.findByCandidate(application.getCandidate()))
					.stream().map(c -> c.getLanguage())
					.collect(Collectors.toList());
			return new ResponseEntity<List<LanguageEntity>>(languages, HttpStatus.OK);
		}
		return null;
	}
	
	//	Vrati skole za aplikaciju
	@GetMapping("/{appId}/educations/")
	public ResponseEntity<?> getSchoolsForApplication(@PathVariable Integer appId) {
		if (applicationRepository.existsById(appId) && !applicationRepository.findById(appId).get().getDeleted()) {
			ApplicationEntity application = applicationRepository.findById(appId).get();
			List<EducationEntity> educations = educationRepository.findByCandidate(application.getCandidate());
			return new ResponseEntity<List<EducationEntity>>(educations, HttpStatus.OK);
		}
		return null;
	}
	
	//	Vrato sertifikate za aplikaciju
	@GetMapping("/{appId}/certificates/")
	public ResponseEntity<?> getCertificatesForApplication(@PathVariable Integer appId) {
		if (applicationRepository.existsById(appId) && !applicationRepository.findById(appId).get().getDeleted()) {
			ApplicationEntity application = applicationRepository.findById(appId).get();
			List<CertificateEntity> certificates = certificateRepository.findByApplication(application);
			return new ResponseEntity<List<CertificateEntity>>(certificates, HttpStatus.OK);
		}
		return null;
	}
	
	//	uploaduj CV za aplikaciju
	@PostMapping("/{appId}/uploadCV/no-security/")
	public ResponseEntity<?> singleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable Integer appId) {
		if (applicationRepository.existsById(appId) && !applicationRepository.findById(appId).get().getDeleted()
				&& applicationRepository.findById(appId).get().getCv() == null) {
			Boolean result = fileHandler.uploadCv(file, appId);
			if (result) {
				return new ResponseEntity<Boolean>(result, HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(result, HttpStatus.BAD_REQUEST);
			}
			
		}
		return null;
	}
	
	//	Preuzmi CV za aplikaciju
	@PostMapping("/{appId}/downloadCV/")
	public ResponseEntity<Resource> downloadCv(@PathVariable Integer appId) {
		if (applicationRepository.existsById(appId) && !applicationRepository.findById(appId).get().getDeleted()) {
			ApplicationEntity app = applicationRepository.findById(appId).get();
			
			if (!cvRepository.existsByApplication(app)) {
				return null;
			}
			
			CvEntity cv = cvRepository.findByApplication(app);
			
			HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.add("content-disposition", "attachment; filename=" + "cv.pdf");
	        
			
			return ResponseEntity.ok()
		            .headers(responseHeaders)
		            .contentLength(cv.getFile().length)
		            .contentType(MediaType.parseMediaType("application/octet-stream"))
		            .body(new ByteArrayResource(cv.getFile()));
		}
		return null;
	}
//	
	
	
	

}
