package com.choco.enstargram.web.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.choco.enstargram.domain.user.User;

import lombok.Data;

@Data
public class SignupRequestDto {
	
	@NotBlank(message="username should not be blank")
	@Size(min=3, max=10, message="invalid username size")
	@Pattern(regexp ="^[a-z][a-z0-9]*$", message="invalid username regex")
	private String username;
	@Size(min=3, max=20, message="invalid password size")
	@NotBlank(message="password should not be blank")
	private String password;
	@NotBlank(message="email should not be blank")
	@Size(min=3, max=20, message="invalid email size")
	@Pattern(regexp="(^[a-z][a-z0-9_-]*[a-z0-9]+)[@]([a-z][a-z0-9_-]*[a-z0-9]+[.][a-z]+$)", message="invalid email regex")
	private String email;
	@NotBlank(message="name should not be blank")
	@Size(min=3, max=10, message="invalid name size")
	@Pattern(regexp="^[a-zA-Z]\\S*$", message="invalid name regex")
	private String name;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
