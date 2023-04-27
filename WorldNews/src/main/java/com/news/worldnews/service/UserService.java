package com.news.worldnews.service;



import org.springframework.stereotype.Service;

import com.news.worldnews.entity.RegistrationRequest;
import com.news.worldnews.entity.User;

@Service
public abstract class UserService {

	public abstract String signUpUser(User user);
	
	

}
