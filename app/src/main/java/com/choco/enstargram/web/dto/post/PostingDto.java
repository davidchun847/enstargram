package com.choco.enstargram.web.dto.post;

import org.springframework.web.multipart.MultipartFile;

import com.choco.enstargram.domain.post.Post;
import com.choco.enstargram.domain.user.User;

import lombok.Data;

@Data
public class PostingDto {
	private MultipartFile file;
	private String text;
	
	public Post toEntity(String imgUrl, User user) {
		return Post.builder()
				.text(text)
				.imgUrl(imgUrl)
				.user(user)
				.build();
	}

}
