package com.news.worldnews.service;

import org.springframework.stereotype.Service;

import com.news.worldnews.entity.Login;
import com.news.worldnews.entity.RegistrationRequest;

import javassist.NotFoundException;

@Service
public interface RegistrationService {

	public String register(RegistrationRequest request) throws Exception;
	public String confirmToken(String token);
	public boolean loginUser(Login login) throws NotFoundException;
	  
}
