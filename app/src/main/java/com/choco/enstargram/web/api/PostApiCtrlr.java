package com.choco.enstargram.web.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choco.enstargram.config.auth.PrincipalDetails;
import com.choco.enstargram.domain.post.Post;
import com.choco.enstargram.service.LikesService;
import com.choco.enstargram.service.PostService;
import com.choco.enstargram.web.dto.CustomRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostApiCtrlr {

	private final PostService postService;
	private final LikesService likesService;

	@GetMapping("/api/post")
	public ResponseEntity<?> news(@AuthenticationPrincipal PrincipalDetails principalDetails,
			@PageableDefault(size=3) Pageable pageable) {
		Page<Post> posts = postService.getNews(principalDetails.getUser().getId(), pageable);
		return new ResponseEntity<>(new CustomRespDto<>(1, "get news success", posts), HttpStatus.OK);
	}
	
	@PostMapping("/api/post/{postId}/likes")
	public ResponseEntity<?> likes(@PathVariable Long postId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		likesService.likes(postId, principalDetails.getUser().getId());
		return new ResponseEntity<>(new CustomRespDto<>(1, "like success", null), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/api/post/{postId}/likes")
	public ResponseEntity<?> dislikes(@PathVariable Long postId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		likesService.dislikes(postId, principalDetails.getUser().getId());
		return new ResponseEntity<>(new CustomRespDto<>(1, "dislike success", null), HttpStatus.OK);
	}
}
