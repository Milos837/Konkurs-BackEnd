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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.konkurs.entities.OfferingEntity;
import com.example.konkurs.entities.PostingEntity;
import com.example.konkurs.entities.RequirementsEntity;
import com.example.konkurs.entities.ResponsibilitiesEntity;
import com.example.konkurs.entities.dto.PostingDto;
import com.example.konkurs.repositories.OfferingRepository;
import com.example.konkurs.repositories.PostingRepository;
import com.example.konkurs.repositories.ReponsibilitiesRepository;
import com.example.konkurs.repositories.RequirementsRepository;
import com.example.konkurs.services.PostingService;

@RestController
@RequestMapping(value = "/api/v1/postings")
public class PostingController {
	
	@Autowired
	private PostingRepository postingRepository;
	
	@Autowired
	private PostingService postingService;
	
	@Autowired
	private ReponsibilitiesRepository responsibilitiesRepository;
	
	@Autowired
	private RequirementsRepository requirementsRepository;
	
	@Autowired
	private OfferingRepository offeringRepository;
	
	//	Vrati sve
	@GetMapping("/no-security/")
	public ResponseEntity<?> getAll() {
		
		List<PostingEntity> postings = ((List<PostingEntity>) postingRepository.findAll())
				.stream().filter(posting -> !posting.getDeleted().equals(true))
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<PostingEntity>>(postings, HttpStatus.OK);
	}
	
	//	Vrati po ID-u
	@GetMapping("/{postingId}/no-security/")
	public ResponseEntity<?> getOne(@PathVariable Integer postingId) {
		if(postingRepository.existsById(postingId) && !postingRepository.findById(postingId).get().getDeleted()) {
			PostingEntity posting = postingRepository.findById(postingId).get();
			return new ResponseEntity<PostingEntity>(posting, HttpStatus.OK);
		}
		return null;
	}
	
	//	Dodaj novi
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody PostingDto newPosting) {
		PostingEntity posting = postingService.save(newPosting);
		return new ResponseEntity<PostingEntity>(posting, HttpStatus.OK);
	}
	
	//	Izmeni
	@PutMapping("/{postingId}")
	public ResponseEntity<?> update(@PathVariable Integer postingId, @RequestBody PostingDto updatedPosting) {
		PostingEntity posting = postingService.update(postingId, updatedPosting);
		if (posting != null) {
			return new ResponseEntity<PostingEntity>(posting, HttpStatus.OK);
		}
		return null;
	}
	
	//	Obrisi (logicki)
	@DeleteMapping("/{postingId}")
	public ResponseEntity<?> delete(@PathVariable Integer postingId) {
		PostingEntity posting = postingService.delete(postingId);
		if (posting != null) {
			return new ResponseEntity<PostingEntity>(posting, HttpStatus.OK);
		}
		return null;
	}
	
	//	Vrati obaveze za posting
	@GetMapping("/{postingId}/responsibilities/no-security/")
	public ResponseEntity<?> getResponsibilitiesForPosting(@PathVariable Integer postingId) {
		if(postingRepository.existsById(postingId) && !postingRepository.findById(postingId).get().getDeleted()) {
			List<ResponsibilitiesEntity> res = responsibilitiesRepository
					.findByPosting(postingRepository.findById(postingId).get());
			return new ResponseEntity<List<ResponsibilitiesEntity>>(res, HttpStatus.OK);
		}
		return null;
	}
	
	//	Vrati uslove za posting
	@GetMapping("/{postingId}/requirements/no-security/")
	public ResponseEntity<?> getRequirementsForPosting(@PathVariable Integer postingId) {
		if(postingRepository.existsById(postingId) && !postingRepository.findById(postingId).get().getDeleted()) {
			List<RequirementsEntity> req = requirementsRepository
					.findByPosting(postingRepository.findById(postingId).get());
			return new ResponseEntity<List<RequirementsEntity>>(req, HttpStatus.OK);
		}
		return null;
	}
	
	//	Vrati ponude za posting
	@GetMapping("/{postingId}/offering/no-security/")
	public ResponseEntity<?> getOfferingForPosting(@PathVariable Integer postingId) {
		if(postingRepository.existsById(postingId) && !postingRepository.findById(postingId).get().getDeleted()) {
			List<OfferingEntity> off = offeringRepository
					.findByPosting(postingRepository.findById(postingId).get());
			return new ResponseEntity<List<OfferingEntity>>(off, HttpStatus.OK);
		}
		return null;
	}
	
	

}
