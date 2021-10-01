package com.bludots.app.rgm.password.registration.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bludots.app.rgm.password.registration.services.EmailService;

@Service
public class EmailSenderService implements EmailService{
	
	@Autowired
	private JavaMailSender mailsender;
	
	public void sendmail(String toEmail,
						String subject,
						String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("abcd@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		
		mailsender.send(message);
	}
	
}
