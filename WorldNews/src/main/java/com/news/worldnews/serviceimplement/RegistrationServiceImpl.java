package com.news.worldnews.serviceimplement;

import java.time.LocalDateTime;

import javax.imageio.spi.RegisterableService;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.worldnews.commons.AppUserRole;
import com.news.worldnews.dao.ConformationTokenRepository;
import com.news.worldnews.dao.UserRepository;
import com.news.worldnews.emailpack.EmailSender;
import com.news.worldnews.emailpack.EmailSending;
import com.news.worldnews.emailpack.EmailValidator;
import com.news.worldnews.entity.ConformationToken;
import com.news.worldnews.entity.Login;
import com.news.worldnews.entity.RegistrationRequest;
import com.news.worldnews.entity.User;
import com.news.worldnews.service.ConformationTokenService;
import com.news.worldnews.service.RegistrationService;
import com.news.worldnews.service.UserService;

import javassist.NotFoundException;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private EmailValidator validator;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserServiceImple userServiceimpl;

	@Autowired
	private ConformationTokenService conformationTokenService;

	@Autowired
	private EmailSender emailSender;

	@Override
	public String register(RegistrationRequest request) throws Exception {

		System.out.println("Request : " + request);

		boolean valid = validator.test(request.getEmail());

		if (!valid) {
			throw new Exception("No valid email");
		}

		String token = userServiceimpl.signUpUser(
				new User(request.getEmail(), request.getFirstname(), request.getLastname(), request.getPassword(),
						request.getPhone(), request.getCity(), request.getState(), request.getZip(), AppUserRole.USER));
		String name = request.getFirstname() + " " + request.lastname;
    System.out.println("Name   :: "+name);
		String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
		emailSender.sendEmail(request.getEmail(), buildEmail(name, link));
		return "Registration Done!!";

	}
	
	
	@Override
	public boolean loginUser(Login login) throws NotFoundException {
		System.out.println("Login Data : "+login.getUsername()+"  "+login.getPassword());
		 User user=userRepository.findByEmail(login.getUsername()).get();
		 System.out.println("User id : "+user.getId());
		 ConformationToken token=conformationTokenService.getByUserId(user.getId());
		 
		 System.out.println("Token : "+token.getToken());
		 if(token.getConfirmTime()==null) {
			 throw new NotFoundException("Please Register Your self");
		 }
		 
		return true;
	}

	 public static String buildEmail(String name, String link) {
		     return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
	                "\n" +
	                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
	                "\n" +
	                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
	                "    <tbody><tr>\n" +
	                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
	                "        \n" +
	                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
	                "          <tbody><tr>\n" +
	                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
	                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
	                "                  <tbody><tr>\n" +
	                "                    <td style=\"padding-left:10px\">\n" +
	                "                  \n" +
	                "                    </td>\n" +
	                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
	                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
	                "                    </td>\n" +
	                "                  </tr>\n" +
	                "                </tbody></table>\n" +
	                "              </a>\n" +
	                "            </td>\n" +
	                "          </tr>\n" +
	                "        </tbody></table>\n" +
	                "        \n" +
	                "      </td>\n" +
	                "    </tr>\n" +
	                "  </tbody></table>\n" +
	                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
	                "    <tbody><tr>\n" +
	                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
	                "      <td>\n" +
	                "        \n" +
	                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
	                "                  <tbody><tr>\n" +
	                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
	                "                  </tr>\n" +
	                "                </tbody></table>\n" +
	                "        \n" +
	                "      </td>\n" +
	                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
	                "    </tr>\n" +
	                "  </tbody></table>\n" +
	                "\n" +
	                "\n" +
	                "\n" +
	                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
	                "    <tbody><tr>\n" +
	                "      <td height=\"30\"><br></td>\n" +
	                "    </tr>\n" +
	                "    <tr>\n" +
	                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
	                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
	                "        \n" +
	                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
	                "        \n" +
	                "      </td>\n" +
	                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
	                "    </tr>\n" +
	                "    <tr>\n" +
	                "      <td height=\"30\"><br></td>\n" +
	                "    </tr>\n" +
	                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
	                "\n" +
	                "</div></div>";
		  }

	@Transactional
	@Override
	public String confirmToken(String token) {
		ConformationToken confirmationToken = conformationTokenService.getToken(token);

//    			orElseThrow(() ->
//    	        new IllegalStateException("token not found"));

		if (confirmationToken.getConfirmTime() != null) {
			throw new IllegalStateException("email already confirmed");
		}

		LocalDateTime expiredAt = confirmationToken.getExpiredTime();

		if (expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("token expired");
		}

		conformationTokenService.setConfirmTime(token);
		userRepository.enableUser(confirmationToken.getUser().getEmail());
		return "confirmed";
	}



}
