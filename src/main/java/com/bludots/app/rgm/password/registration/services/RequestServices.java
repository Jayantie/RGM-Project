package com.bludots.app.rgm.password.registration.services;

import org.springframework.stereotype.Service;

import com.bludots.app.rgm.password.registration.repositories.entities.Request;
import com.bludots.app.rgm.password.registration.valueobjects.RequestVO;

public interface RequestServices {
	public Request persistRequest(RequestVO vo);
	public Request findRequest (String token) ;
}
