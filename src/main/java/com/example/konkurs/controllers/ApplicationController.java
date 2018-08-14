package com.example.konkurs.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.konkurs.entities.ApplicationEntity;
import com.example.konkurs.entities.dto.ApplicationDto;
import com.example.konkurs.repositories.ApplicationRepository;
import com.example.konkurs.repositories.PostingRepository;
import com.example.konkurs.services.ApplicationService;

@RestController
@RequestMapping(value = "/api/v1/applications")
public class ApplicationController {
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private PostingRepository postingRepository;
	
	@Autowired
	private ApplicationService applicationService;
	
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
	@PostMapping("/postings/{postingId}")
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

}
