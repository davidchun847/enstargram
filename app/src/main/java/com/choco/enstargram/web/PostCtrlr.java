package com.choco.enstargram.web;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.choco.enstargram.config.auth.PrincipalDetails;
import com.choco.enstargram.domain.post.Post;
import com.choco.enstargram.handler.exception.CustomValidException;
import com.choco.enstargram.service.PostService;
import com.choco.enstargram.web.dto.post.PostingDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostCtrlr {
	
	private final PostService postService;

	@GetMapping({"/", "/post/home"})
	public String home() {
		return "post/home";
	}
	
	@GetMapping({"/post/popular"})
	public String popular(Model model) {
		List<Post> posts = postService.getPopulars();
		model.addAttribute("posts", posts);
		return "post/popular";
	}
	
	@GetMapping({"/post/upload"})
	public String upload() {
		return "post/upload";
	}
	
	@PostMapping("/post")
	public String post(PostingDto postingDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		if (postingDto.getFile().isEmpty() ) {
			throw new CustomValidException("no img uploaded");
		}
		postService.post(postingDto, principalDetails);
		return "redirect:/user/" + principalDetails.getUser().getId();
	}
}
