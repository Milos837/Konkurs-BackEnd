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

import com.example.konkurs.entities.PostingEntity;
import com.example.konkurs.entities.dto.PostingDto;
import com.example.konkurs.repositories.PostingRepository;
import com.example.konkurs.services.PostingService;

@RestController
@RequestMapping(value = "/api/v1/postings")
public class PostingController {
	
	@Autowired
	private PostingRepository postingRepository;
	
	@Autowired
	private PostingService postingService;
	
	//	Vrati sve
	@GetMapping("/")
	public ResponseEntity<?> getAll() {
		
		List<PostingEntity> postings = ((List<PostingEntity>) postingRepository.findAll())
				.stream().filter(posting -> !posting.getDeleted().equals(true))
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<PostingEntity>>(postings, HttpStatus.OK);
	}
	
	//	Vrati po ID-u
	@GetMapping("/{postingId}")
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
	
	

}
