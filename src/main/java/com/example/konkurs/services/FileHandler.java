package com.example.konkurs.services;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface FileHandler {
	
	public Boolean uploadCv(MultipartFile file, Integer appId);
	
	public byte[] getCv(Integer appId);

}
