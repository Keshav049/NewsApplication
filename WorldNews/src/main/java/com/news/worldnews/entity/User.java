package com.news.worldnews.entity;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.news.worldnews.commons.AppUserRole;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "UserTable")
public class User implements UserDetails {

	
	@SequenceGenerator(
			name = "student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1
	)
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence"
			)
	public Long id;
	public String email;
	public String firstname;
	public String lastname;
	public String password;
	public String phone;
	private String city;
	private String state;
	private String zip;
	
	@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole;
	private boolean locked;
	private boolean enable;

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}




	public User(String email, String firstname, String lastname, String password, String phone, String city, String state,
			String zip, AppUserRole appUserRole) {

		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.phone = phone;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.appUserRole = appUserRole;
	
	}

   


	public void setId(Long id) {
		this.id = id;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}




	public void setLastname(String lastname) {
		this.lastname = lastname;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public void setState(String state) {
		this.state = state;
	}




	public void setZip(String zip) {
		this.zip = zip;
	}




	public void setAppUserRole(AppUserRole appUserRole) {
		this.appUserRole = appUserRole;
	}




	public void setLocked(boolean locked) {
		this.locked = locked;
	}




	public void setEnable(boolean enable) {
		this.enable = enable;
	}




	public Long getId() {
		return id;
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




	public AppUserRole getAppUserRole() {
		return appUserRole;
	}




	public boolean isLocked() {
		return locked;
	}




	public boolean isEnable() {
		return enable;
	}




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
		return Collections.singletonList(authority);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enable;
	}

}
