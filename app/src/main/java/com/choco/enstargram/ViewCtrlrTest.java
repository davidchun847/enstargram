package com.choco.enstargram;

import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class ViewCtrlrTest {

	@GetMapping("/auth/signup")
	public String authSignupPage() {
		return "auth/signup";
	}
	
	@GetMapping("/auth/signin")
	public String authSigninPage() {
		return "auth/signin";
	}
	
	@GetMapping("/post/home")
	public String postHomePage() {
		return "post/home";
	}
	
	@GetMapping("/post/popular")
	public String postPopularPage() {
		return "post/popular";
	}
	
	@GetMapping("/post/upload")
	public String postUploadPage() {
		return "post/upload";
	}
	
	@GetMapping("/user/profile")
	public String userProfilePage() {
		return "user/profile";
	}

	@GetMapping("/user/profileupdate")
	public String userProfileUpdatePage() {
		return "user/profileupdate";
	}
}
