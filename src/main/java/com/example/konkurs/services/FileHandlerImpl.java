package com.example.konkurs.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileHandlerImpl implements FileHandler{
	
	private static String CV_FOLDER = "/home/milos/Java/spring-workspace/konkurs/cv/";
	
	public String uploadCv(MultipartFile file) {
		
		try {
			
			Long time = System.currentTimeMillis();
			
			String fileName = time.toString();
			
			byte[] bytes = file.getBytes();
			Path path = Paths.get(CV_FOLDER + fileName + ".pdf");
            Files.write(path, bytes);
            
            return path.toString();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}

}
