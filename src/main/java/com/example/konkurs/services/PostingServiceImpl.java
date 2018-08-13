package com.example.konkurs.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.konkurs.entities.OfferingEntity;
import com.example.konkurs.entities.PostingEntity;
import com.example.konkurs.entities.RequirementsEntity;
import com.example.konkurs.entities.ResponsibilitiesEntity;
import com.example.konkurs.entities.dto.PostingDto;
import com.example.konkurs.repositories.OfferingRepository;
import com.example.konkurs.repositories.PostingRepository;
import com.example.konkurs.repositories.ReponsibilitiesRepository;
import com.example.konkurs.repositories.RequirementsRepository;

@Service
public class PostingServiceImpl implements PostingService{
	
	@Autowired
	private PostingRepository postingRepository;
	
	@Autowired
	private ReponsibilitiesRepository responsibilitiesRepository;
	
	@Autowired
	private RequirementsRepository requirementsRepository;
	
	@Autowired
	private OfferingRepository offeringRepository;
	
	public PostingEntity save(PostingDto newPosting) {
		PostingEntity posting = new PostingEntity();
		posting.setName(newPosting.getName());
		posting.setDate(LocalDate.now());
		posting.setDeleted(false);
		posting = postingRepository.save(posting);
		
		for (String responsibility : newPosting.getResponsibilities()) {
			ResponsibilitiesEntity resp = new ResponsibilitiesEntity();
			resp.setResponsibility(responsibility);
			resp.setPosting(posting);
			resp.setDeleted(false);
			responsibilitiesRepository.save(resp);
		}
		
		for (String requirement : newPosting.getRequirements()) {
			RequirementsEntity req = new RequirementsEntity();
			req.setRequirement(requirement);
			req.setPosting(posting);
			req.setDeleted(false);
			requirementsRepository.save(req);
		}
		
		for (String offering : newPosting.getOffering()) {
			OfferingEntity off = new OfferingEntity();
			off.setOffering(offering);
			off.setPosting(posting);
			off.setDeleted(false);
			offeringRepository.save(off);
		}
		return posting;
	}
	
	public PostingEntity update(Integer postingId, PostingDto updatedPosting) {
		if(postingRepository.existsById(postingId) && !postingRepository.findById(postingId).get().getDeleted()) {
			PostingEntity posting = postingRepository.findById(postingId).get();
			
			responsibilitiesRepository.deleteByPosting(posting);
			requirementsRepository.deleteByPosting(posting);
			offeringRepository.deleteByPosting(posting);
			
			posting.setName(updatedPosting.getName());
			posting.setDate(LocalDate.now());
			postingRepository.save(posting);
			
			for (String responsibility : updatedPosting.getResponsibilities()) {
				ResponsibilitiesEntity resp = new ResponsibilitiesEntity();
				resp.setResponsibility(responsibility);
				resp.setPosting(posting);
				resp.setDeleted(false);
				responsibilitiesRepository.save(resp);
			}
			
			for (String requirement : updatedPosting.getRequirements()) {
				RequirementsEntity req = new RequirementsEntity();
				req.setRequirement(requirement);
				req.setPosting(posting);
				req.setDeleted(false);
				requirementsRepository.save(req);
			}
			
			for (String offering : updatedPosting.getOffering()) {
				OfferingEntity off = new OfferingEntity();
				off.setOffering(offering);
				off.setPosting(posting);
				off.setDeleted(false);
				offeringRepository.save(off);
			}
			return posting;
		}
		return null;
	}
	
	public PostingEntity delete(Integer postingId) {
		if(postingRepository.existsById(postingId) && !postingRepository.findById(postingId).get().getDeleted()) {
			PostingEntity posting = postingRepository.findById(postingId).get();
			posting.setDeleted(true);
			postingRepository.save(posting);
			return posting;
		}
		return null;
	}
	
	

}
