package com.choco.enstargram.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choco.enstargram.domain.user.User;
import com.choco.enstargram.service.AuthService;
import com.choco.enstargram.web.dto.auth.SignupRequestDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthCtrlr {

	private final AuthService authService;
		
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}

	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}

	@PostMapping("/auth/signup")
	public @ResponseBody String signup(@Valid SignupRequestDto reqDto, BindingResult bindRes) { // key=value x-www-form-urlencoded
		
			User user = reqDto.toEntity();
			User userEntity = authService.registerAccount(user);
			System.out.println(userEntity);
			return "auth/signin";
	}

	
}
