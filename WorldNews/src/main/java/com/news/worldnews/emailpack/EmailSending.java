package com.news.worldnews.emailpack;



import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailSending implements EmailSender {
	
	private final Logger logger=LoggerFactory.getLogger(EmailSending.class);
	
	private final JavaMailSender javaMailSender ;
	
	public EmailSending(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}

	@Override
	@Async
	public void sendEmail(String to, String email) throws Exception {
	
		try {
//			System.out.println("mail seend step 1");
//			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//			System.out.println("mail seend step 2");
//			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage , "utf-8");
//			System.out.println("mail seend step 3");
			SimpleMailMessage helper=new SimpleMailMessage();
			helper.setText(email);
			helper.setTo(to);
			helper.setSubject("please confirm your email");
			helper.setFrom("wadwalekeshav2019@gmail.com");
			javaMailSender.send(helper);
			
			System.out.println("Hi I have Send Mail");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("mail not send " ,e);
			throw new Exception("email not send");
		}
		
		
	}

	public Logger getLogger() {
		return logger;
	}

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}
	

}
