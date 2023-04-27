package com.news.worldnews.emailpack;

import org.springframework.stereotype.Service;

@Service
public interface EmailSender {
	 public void sendEmail(String to, String email) throws Exception;
}
