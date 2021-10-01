package com.bludots.app.rgm.password.registration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bludots.app.rgm.password.registration.repositories.entities.Credential;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {
	public Credential findByEmail(String email);

}
