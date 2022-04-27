package com.choco.enstargram.domain.post;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.choco.enstargram.domain.comment.Comment;
import com.choco.enstargram.domain.likes.Likes;
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
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String text;
	private String imgUrl;
	
	@JsonIgnoreProperties({"posts"})
	@JoinColumn(name="userId")
	@ManyToOne
	private User user;
	
	
	@JsonIgnoreProperties({"post"})
	@OneToMany(mappedBy = "post")
	private List<Likes> likes;
	
	@OrderBy("id DESC")
	@JsonIgnoreProperties({"post"})
	@OneToMany(mappedBy = "post")
	private List<Comment> comments;
	
	@Transient // column not created in DB
	private boolean likeState;
	
	@Transient
	private int numLikes;

	private LocalDateTime createTime;

	@PrePersist
	public void createTimeNow() {
		this.createTime = LocalDateTime.now();
	}
}
