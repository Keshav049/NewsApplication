package com.news.worldnews.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class ConformationToken {

	@SequenceGenerator(
			name = "conformation_token_sequence",
			sequenceName = "conformation_token_sequence",
			allocationSize = 1
	)
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "conformation_token_sequence"
			)
	private long id;
	
	@Column(nullable = false)
	private String token;
	
	@Column(nullable = false)
	private LocalDateTime createdDate;
	
	@Column(nullable = false)
	private LocalDateTime expiredTime;
	
	
	private LocalDateTime confirmTime;
	
	@ManyToOne
	@JoinColumn(nullable = false,name = "user_id")
	private User user;

	public ConformationToken(String token,LocalDateTime createdDate, LocalDateTime expiredTime, User user) {
		
		this.token = token;
		this.createdDate=createdDate;
	
		this.expiredTime = expiredTime;
		this.user=user;
	}

	
	public LocalDateTime getConfirmTime() {
		return confirmTime;
	}


	public void setConfirmTime(LocalDateTime confirmTime) {
		this.confirmTime = confirmTime;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(LocalDateTime expiredTime) {
		this.expiredTime = expiredTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public ConformationToken() {

		// TODO Auto-generated constructor stub
	}
	
	
	
}
