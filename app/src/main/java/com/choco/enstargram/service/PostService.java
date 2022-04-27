package com.choco.enstargram.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choco.enstargram.config.auth.PrincipalDetails;
import com.choco.enstargram.domain.post.Post;
import com.choco.enstargram.domain.post.PostRepo;
import com.choco.enstargram.web.dto.post.PostingDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
	
	@Value("${file.img.post_dir}")
	private String imgDir;
	
	private final PostRepo postRepo;

	@Transactional
	public void post(PostingDto postingDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID();
		String imgFileName = uuid + "_" + postingDto.getFile().getOriginalFilename();
		System.out.println("img file name:" + imgFileName);
		Path imgFilePath = Paths.get(imgDir + imgFileName);
		
		try {
			Files.write(imgFilePath, postingDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Post post = postingDto.toEntity(imgFileName, principalDetails.getUser());
		postRepo.save(post);
	}
	
	@Transactional(readOnly=true)
	public Page<Post> getNews(Long principalId, Pageable pageable){
		Page<Post> posts = postRepo.getNews(principalId, pageable);
		posts.forEach((post)->{
			post.setNumLikes(post.getLikes().size());
			post.getLikes().forEach((like)-> {
				if (like.getUser().getId() == principalId) {
					post.setLikeState(true);
				}
			});
		});
		return posts;
	}
	
	@Transactional(readOnly=true)
	public List<Post> getPopulars() {
		return postRepo.getPopulars();
	}

}
