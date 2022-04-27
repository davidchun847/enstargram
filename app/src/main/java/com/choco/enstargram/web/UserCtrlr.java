package com.choco.enstargram.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.choco.enstargram.config.auth.PrincipalDetails;
import com.choco.enstargram.service.UserService;
import com.choco.enstargram.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserCtrlr {
	
	private final UserService userService;
	
	@GetMapping("/user/{pageUserId}")
	public String profile(@PathVariable Long pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		UserProfileDto userProfileDto = userService.getProfile(pageUserId, principalDetails.getUser().getId());
		model.addAttribute("profileDto", userProfileDto);
		return "user/profile";
	}
	
	@GetMapping("/user/{userId}/profilemod")
	public String profileMod(@PathVariable Long userId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		return "user/profilemod";
	}
	
}
