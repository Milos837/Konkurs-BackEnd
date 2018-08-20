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
import com.example.konkurs.repositories.ApplicationRepository;

@Service
public class FileHandlerImpl implements FileHandler{
	
	private static String CV_FOLDER = "/home/milos/Java/spring-workspace/konkurs/cv/";
	//private static String CV_FOLDER = "C:\\Java\\spring-workspace\\konkurs-backend\\cv\\";
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	public Boolean uploadCv(MultipartFile file, Integer appId) {
		
		try {
			
			Long time = System.currentTimeMillis();
			
			String fileName = time.toString();
			
			byte[] bytes = file.getBytes();
			Path path = Paths.get(CV_FOLDER + fileName + ".pdf");
            Files.write(path, bytes);
            
            if (applicationRepository.existsById(appId)) {
            	ApplicationEntity app = applicationRepository.findById(appId).get();
            	app.setCv(path.toString());
            	applicationRepository.save(app);
            }
            
            return true;
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public File getCv(Integer appId) {
		
		String path = applicationRepository.findById(appId).get().getCv();
		
		File cv = new File(path);
		
		return cv;
	}

}
