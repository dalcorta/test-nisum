package com.nisum.examen.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nisum.examen.entities.TelephoneEntity;

public interface TelephoneRepository extends JpaRepository<TelephoneEntity, UUID> {

}
