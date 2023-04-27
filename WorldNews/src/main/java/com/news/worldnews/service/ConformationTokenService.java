package com.news.worldnews.service;

import org.springframework.stereotype.Service;

import com.news.worldnews.entity.ConformationToken;

@Service
public interface ConformationTokenService {
	
	public ConformationToken saveConformationToken(ConformationToken token);

	public ConformationToken getToken(String token);
	
	public int setConfirmTime(String token);
	
	public ConformationToken getByUserId(long userId);

	
}
