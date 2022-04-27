package com.choco.enstargram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choco.enstargram.domain.likes.LikesRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikesService {
	
	private final LikesRepo likesRepo;
	
	@Transactional
	public void likes(Long postId, Long principalId) {
		likesRepo.likes(postId, principalId);
	}
	
	@Transactional
	public void dislikes(Long postId, Long principalId) {
		likesRepo.dislikes(postId, principalId);
	}

}
