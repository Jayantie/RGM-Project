package com.bludots.app.rgm.password.registration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bludots.app.rgm.password.registration.repositories.entities.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
	Request findByToken(String token);
}
