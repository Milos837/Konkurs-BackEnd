package com.example.konkurs.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileHandler {
	
	public String uploadCv(MultipartFile file);

}
