package com.bludots.app.rgm.password.registration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bludots.app.rgm.password.registration.repositories.entities.RollTable;

public interface RollRepository extends JpaRepository<RollTable, Long>  {

}
