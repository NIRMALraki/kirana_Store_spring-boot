package com.kirana.transaction.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.kirana.transaction.service.AppUserDetailService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
	
	@Autowired
	AppUserDetailService userDetailService;
	
	@Bean
    AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider daoauthenticationProvider = new DaoAuthenticationProvider();
		daoauthenticationProvider.setUserDetailsService(userDetailService);
		daoauthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return daoauthenticationProvider;
	}
	
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
    	http.csrf(csrf->csrf.disable());
    	http.authorizeHttpRequests(auth-> {
    	auth.requestMatchers("/api/v1/register","/h2-console/**")
    	.permitAll()
    	.anyRequest()
    	.authenticated();
    	});

        http.httpBasic(withDefaults());
        http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
    	http.headers(
    	headers->headers.frameOptions(
    	frame -> frame.disable()));
        
        return http.build();
    }

}
