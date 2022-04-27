package com.choco.enstargram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choco.enstargram.domain.comment.Comment;
import com.choco.enstargram.domain.comment.CommentRepo;
import com.choco.enstargram.domain.post.Post;
import com.choco.enstargram.domain.user.User;
import com.choco.enstargram.domain.user.UserRepo;
import com.choco.enstargram.handler.exception.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	
	private final CommentRepo commentRepo;
	private final UserRepo userRepo;
	
	@Transactional
	public Comment leaveComment(String content, Long postId, Long userId) {
		
		// create dummy entity only postId saved in the DB
		Post post = new Post();
		post.setId(postId);
		
		User user = userRepo.findById(userId).orElseThrow(()->{
			throw new CustomApiException("leave comment: user id cannot found");
		});
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setPost(post);
		comment.setUser(user);
		return commentRepo.save(comment);
	}
	
	@Transactional
	public void delComment(Long id) {
		try {
			commentRepo.deleteById(id);
		} catch (Exception e) {
			throw new CustomApiException(e.getMessage());
		}
	}
}
