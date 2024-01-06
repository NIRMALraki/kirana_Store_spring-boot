package com.kirana.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kirana.transaction.entity.User;
import com.kirana.transaction.repository.UserRepository;
import com.kirana.transaction.security.UserPrinciple;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppUserDetailService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(email);
		if(user==null)
		{
			log.error(email+" not found");
			throw new UsernameNotFoundException("user not found");
		}
		return new UserPrinciple(user);
	}
	
	

}
