package com.bludots.app.rgm.password.registration.services;

import org.springframework.stereotype.Service;

public interface EmailService {
	void sendmail (String toEmail,
			String subject,
			String body);

}
