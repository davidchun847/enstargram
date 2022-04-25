package com.choco.enstargram.domain.likes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(uniqueConstraints = { @UniqueConstraint(name = "likes_uk", columnNames = { "postId", "userId" }) })
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "postId")
	@ManyToOne
	private Post post;
	
	@JsonIgnoreProperties({"posts"})
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user;

	private LocalDateTime createTime;

	@PrePersist
	public void createTimeNow() {
		this.createTime = LocalDateTime.now();
	}
}
