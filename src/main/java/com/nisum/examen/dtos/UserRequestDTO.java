package com.nisum.examen.dtos;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserRequestDTO {

	private String name;

	@Email(message = "Email is invalid")
	private String email;
	
	@Pattern(regexp = "^(?=.*[0-9]).{8,}$", message = "Password is invalid.")
	private String password;

	private Set<TelephoneDTO> phones;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the telephones
	 */
	public Set<TelephoneDTO> getPhones() {
		return phones;
	}

	/**
	 * @param telephones the telephones to set
	 */
	public void setPhones(Set<TelephoneDTO> phones) {
		this.phones = phones;
	}

}
