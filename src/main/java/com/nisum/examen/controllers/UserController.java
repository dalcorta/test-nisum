package com.nisum.examen.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.examen.dtos.UserRequestDTO;
import com.nisum.examen.dtos.UserResponseDTO;
import com.nisum.examen.services.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	private UserService service;
	
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid UserRequestDTO user) {
		UserResponseDTO body = service.create(user);
		return ResponseEntity.ok(body);
	}
}
