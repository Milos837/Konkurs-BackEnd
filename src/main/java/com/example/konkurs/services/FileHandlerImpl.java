package com.example.konkurs.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.konkurs.entities.ApplicationEntity;
import com.example.konkurs.entities.CvEntity;
import com.example.konkurs.repositories.ApplicationRepository;
import com.example.konkurs.repositories.CvRepository;

@Service
public class FileHandlerImpl implements FileHandler{
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private CvRepository cvRepository;
	
	public Boolean uploadCv(MultipartFile file, Integer appId) {
		
		try {
			
			byte[] bytes = file.getBytes();
			
            
            if (applicationRepository.existsById(appId)) {
            	ApplicationEntity app = applicationRepository.findById(appId).get();
            	CvEntity cv = new CvEntity();
            	cv.setDeleted(false);
            	cv.setApplication(app);
            	cv.setFile(bytes);
            	cvRepository.save(cv);
            }
            
            return true;
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public byte[] getCv(Integer appId) {
		
		CvEntity cv = cvRepository.findByApplication(applicationRepository.findById(appId).get());
		
		return cv.getFile();
	}

}
