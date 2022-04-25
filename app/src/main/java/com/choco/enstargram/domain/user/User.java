package com.choco.enstargram.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;

import com.choco.enstargram.domain.post.Post;
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
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=100, unique=true)
	private String username;
	@Column(nullable=false)
	private String password;
	@Column(length=20, nullable=false)
	private String email;
	@Column(length=30, nullable=false)
	private String name;
	private String website;
	private String bio;
	@Column(length=15)
	private String phone;
	private String gender;
	private String profileImgUrl;
	private String role;
	
	@OrderBy("id DESC")
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"user"})
	private List<Post> posts;
	
	private LocalDateTime createTime;
	
	@PrePersist
	public void createTimeNow() {
		this.createTime = LocalDateTime.now();
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", name="
				+ name + ", website=" + website + ", bio=" + bio + ", phone=" + phone + ", gender=" + gender
				+ ", profileImgUrl=" + profileImgUrl + ", role=" + role + ", createTime=" + createTime + "]";
	}
}

