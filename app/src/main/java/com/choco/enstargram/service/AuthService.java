package com.choco.enstargram.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.choco.enstargram.domain.user.User;
import com.choco.enstargram.domain.user.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepo userRepo;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public User registerAccount(User user) {
		String rawPw = user.getPassword();
		String encPw = passwordEncoder.encode(rawPw);
		user.setPassword(encPw);
		user.setRole("ROLE_USER");
		User userEntity = userRepo.save(user);
		return userEntity;
	}
}
