package com.news.worldnews.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




@EqualsAndHashCode
@ToString
public class RegistrationRequest {

	
	public String email;
	public String firstname;
	public String lastname;
	public String password;
	public String phone;
	private String city;
	private String state;
	private String zip;
	


	public RegistrationRequest(String email, String firstname, String lastname, String password, String phone,
			String city, String state, String zip) {
		super();
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.phone = phone;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}


	public String getEmail() {
		return email;
	}


	public String getFirstname() {
		return firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public String getPassword() {
		return password;
	}


	public String getPhone() {
		return phone;
	}


	public String getCity() {
		return city;
	}


	public String getState() {
		return state;
	}


	public String getZip() {
		return zip;
	}

}
