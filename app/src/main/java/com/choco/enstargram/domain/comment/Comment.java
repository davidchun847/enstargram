package com.choco.enstargram.domain.comment;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.choco.enstargram.domain.post.Post;
import com.choco.enstargram.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=100, nullable=false)
	private String content;
	
	
	@JsonIgnoreProperties({"posts"})
	@JoinColumn(name="userId")
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	
	@JoinColumn(name="postId")
	@ManyToOne(fetch=FetchType.EAGER)
	private Post post;
	

	private LocalDateTime createTime;

	@PrePersist
	public void createTimeNow() {
		this.createTime = LocalDateTime.now();
	}
}
