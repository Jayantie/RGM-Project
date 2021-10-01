package com.bludots.app.rgm.password.registration.services;

import org.springframework.stereotype.Service;

import com.bludots.app.rgm.password.registration.valueobjects.CredentialVO;

public interface CredentialService {

	public Boolean persistPasswordChange(CredentialVO vo);
}
