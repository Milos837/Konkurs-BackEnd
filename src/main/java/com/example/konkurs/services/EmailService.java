package com.example.konkurs.services;

import com.example.konkurs.entities.util.EmailObject;

public interface EmailService {
	
	public void newAppNotification(Integer appId);
	
	public void sendSimpleEmail(EmailObject email);

}
