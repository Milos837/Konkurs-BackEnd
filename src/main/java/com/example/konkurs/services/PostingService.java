package com.example.konkurs.services;

import com.example.konkurs.entities.PostingEntity;
import com.example.konkurs.entities.dto.PostingDto;

public interface PostingService {
	
	public PostingEntity save(PostingDto newPosting);
	
	public PostingEntity update(Integer postingId, PostingDto updatedPosting);
	
	public PostingEntity delete(Integer postingId);

}
