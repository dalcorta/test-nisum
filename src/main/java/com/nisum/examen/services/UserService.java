package com.nisum.examen.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.examen.dtos.TelephoneDTO;
import com.nisum.examen.dtos.UserRequestDTO;
import com.nisum.examen.dtos.UserResponseDTO;
import com.nisum.examen.entities.TelephoneEntity;
import com.nisum.examen.entities.UserEntity;
import com.nisum.examen.repositories.UserRepository;

import io.jsonwebtoken.Jwts;

@Service
public class UserService {

	private UserRepository repository;

	@Autowired
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public UserResponseDTO create(UserRequestDTO user) {
		final UserEntity entity = new UserEntity();
		entity.setEmail(user.getEmail());
		entity.setName(user.getName());
		entity.setPassword(user.getPassword());
		entity.setToken(Jwts.builder().setSubject(user.getName()).claim("email", user.getEmail()).compact());
		entity.setTelephones(user.getPhones().stream().map(t -> {
			TelephoneEntity telephone = new TelephoneEntity();
			telephone.setUser(entity);
			telephone.setCityCode(t.getCityCode());
			telephone.setCountryCode(t.getCountryCode());
			telephone.setNumber(t.getNumber());
			return telephone;
		}).collect(Collectors.toSet()));
		entity.setIsActive(true);

		UserEntity persistedEntity = repository.save(entity);

		UserResponseDTO response = new UserResponseDTO();
		response.setCreated(persistedEntity.getCreated());
		response.setEmail(persistedEntity.getEmail());
		response.setId(persistedEntity.getId().toString());
		response.setIsActive(persistedEntity.getIsActive());
		response.setLastLogin(persistedEntity.getLastLogin());
		response.setModified(persistedEntity.getModified());
		response.setName(persistedEntity.getName());
		response.setPassword(persistedEntity.getPassword());
		response.setToken(persistedEntity.getToken());
		response.setPhones(persistedEntity.getTelephones().stream().map(t -> {
			TelephoneDTO phone = new TelephoneDTO();
			phone.setCityCode(t.getCityCode());
			phone.setCountryCode(t.getCountryCode());
			phone.setNumber(t.getNumber());
			return phone;
		}).collect(Collectors.toSet()));

		return response;
	}
}
