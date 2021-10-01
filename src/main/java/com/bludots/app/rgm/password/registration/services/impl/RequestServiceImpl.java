package com.bludots.app.rgm.password.registration.services.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bludots.app.rgm.password.registration.repositories.RequestRepository;
import com.bludots.app.rgm.password.registration.repositories.entities.Request;
import com.bludots.app.rgm.password.registration.services.EmailService;
import com.bludots.app.rgm.password.registration.services.RequestServices;
import com.bludots.app.rgm.password.registration.token.TokenGenerator;
import com.bludots.app.rgm.password.registration.valueobjects.RequestVO;

@Transactional
@Service
public class RequestServiceImpl implements RequestServices {
	
	@Autowired
	private RequestRepository requestRepository;
	
	private TokenGenerator tokengeneratie= new TokenGenerator(); 
	
	@Autowired
	private EmailService sender; 
	
	public RequestServiceImpl(EmailService sender) {
		super();
		this.sender = sender;
	}
	
	@Override
	public Request persistRequest(RequestVO vo) {
		Request request = new Request();
		request.setEmail(vo.getEmail());
		request.setRequestDateTime(LocalDateTime.now());

//SEND TOKEN
		String token = tokengeneratie.token();
		request.setToken(token);
		
//SEND MAIL
		String link =  "http://localhost:9800/rgm.password/PasswordFormView?token=" +token;
	    sender.sendmail(
	    		request.getEmail(),"test mail",
	    		buildEmail(request.getEmail(), link));
	    
	    return requestRepository.save(request);
	    		}
	 public String buildEmail (String email, String link) {
		 return "Email: " + email + "<a href=" + link + " target=\\\"_blank\\\">click this link</a> ";
	 }

	@Override
	public Request findRequest(String token) {
		// TODO Auto-generated method stub
		Request vindtoken = requestRepository.findByToken(token);
		return vindtoken;
	}
	 

}
