package com.news.worldnews.serviceimplement;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//import com.news.News.entity.Token;
import com.news.worldnews.dao.UserRepository;
import com.news.worldnews.entity.ConformationToken;
import com.news.worldnews.entity.User;
import com.news.worldnews.service.ConformationTokenService;
import com.news.worldnews.service.UserService;

@Service
public class UserServiceImple extends UserService implements UserDetailsService {

	private final String USER_NOT_FOUND = "user with email %s not fount";

	@Autowired
	private ConformationTokenService conformationTokenService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
	}

	@Override
	public String signUpUser(User user) {
		String pass = user.getPassword();
		boolean isExits = userRepository.findByEmail(user.getEmail()).isPresent();

		if (isExits) {
//			boolean existCheck = userRepository.findByEmailAndEnable(user.getEmail(), false).isPresent();
			System.out.println();
			if (true) {
				User userExits = userRepository.findByEmail(user.getEmail()).get();

				String token = UUID.randomUUID().toString();
				System.out.println("Token : "+token);

				ConformationToken confirmToken = new ConformationToken(token, LocalDateTime.now(),
						LocalDateTime.now().plusMinutes(15), userExits);

				conformationTokenService.saveConformationToken(confirmToken);
//                System.out.println("Token in true  :  "+t);
				return token;

			}

			throw new IllegalStateException("Email Already Taken");
		}

		String EncodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

		user.setPassword(EncodedPassword);
		

		userRepository.save(user);
//		System.out.println("Token 11: "+user);
		String token = UUID.randomUUID().toString();
		System.out.println("Token : "+token);
		ConformationToken confirmToken = new ConformationToken(
				token, 
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				user);

		System.out.println("im here");
		conformationTokenService.saveConformationToken(confirmToken);
        System.out.println("Im conform");
		// TODO : Send Email
		return token;

	}

}
