package com.choco.enstargram.web.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.choco.enstargram.config.auth.PrincipalDetails;
import com.choco.enstargram.domain.comment.Comment;
import com.choco.enstargram.service.CommentService;
import com.choco.enstargram.web.dto.CustomRespDto;
import com.choco.enstargram.web.dto.comment.CommentDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentApiCtrlr {
	
	private final CommentService commentService;
	
	@PostMapping("/api/comment")
	public ResponseEntity<?> leaveComment(@Valid @RequestBody CommentDto commentDto, BindingResult bindRes, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		// add reuqest body arg annotation to get json data instead of www-x form
		Comment comment = commentService.leaveComment(commentDto.getContent(), commentDto.getPostId(), principalDetails.getUser().getId());
		return new ResponseEntity<>(new CustomRespDto<>(1, "comment leave write success", comment), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/api/comment/{id}")
	public ResponseEntity<?> delComment(@PathVariable Long id) {
		commentService.delComment(id);
		return new ResponseEntity<>(new CustomRespDto<>(1, "comment leave del success", null), HttpStatus.OK);
	}

}
