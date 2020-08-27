package com.nisum.examen.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nisum.examen.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
