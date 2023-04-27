package com.news.worldnews.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.news.worldnews.serviceimplement.UserServiceImple;

import lombok.AllArgsConstructor;


@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserServiceImple userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http
	     .csrf()
	     .disable()
	     .authorizeRequests()
	     .antMatchers("/api/v1*/registration/**","/h2-console/**","/api/v1*/registration/confirm/**","/login/**")
	     .permitAll()
	     .anyRequest()
	     .authenticated()
	     .and()
	     .formLogin();
	

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	

//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		
//		http
//		     .csrf()
//		     .disable()
//		     .authorizeRequests()
//		     .antMatchers("/api/v1*/registration/**")
//		     .permitAll()
//		     .anyRequest()
//		     .authenticated()
//		     .and()
//		     .formLogin();
//		
//		return http.build();
//	}
//	
//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
//		return (web)->web.ignoring().antMatchers("/image/**","/js/**","/webjars/**");
//	}
//	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bCryptPasswordEncoder);
		provider.setUserDetailsService(userService);
		return provider;
	}
}
