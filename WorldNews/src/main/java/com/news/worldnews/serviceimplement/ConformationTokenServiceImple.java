package com.news.worldnews.serviceimplement;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.worldnews.dao.ConformationTokenRepository;
import com.news.worldnews.entity.ConformationToken;
import com.news.worldnews.service.ConformationTokenService;


@Service
public class ConformationTokenServiceImple implements ConformationTokenService {

	@Autowired
	private ConformationTokenRepository conformationTokenRepository;

	@Override
	public ConformationToken saveConformationToken(ConformationToken token) {
		System.out.println("Conform token : "+token);
		
		return conformationTokenRepository.save(token);
	}

	@Override
	public ConformationToken getToken(String token) {
//		System.out.println("finding token");
		// TODO Auto-generated method stub
		return conformationTokenRepository.findByToken(token).get();
	}
	

	@Override
	public int setConfirmTime(String token) {
	        return conformationTokenRepository.updateConfirmedAt(
	                token, LocalDateTime.now());
	    }

	@Override
	public ConformationToken getByUserId(long userId) {
		System.out.println("Hi im login");
		return conformationTokenRepository.findByUserId(userId);
	}
}
