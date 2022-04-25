package com.choco.enstargram.web.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.choco.enstargram.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateRequestDto {
	
	@NotBlank(message="name should not be blank")
	@Size(min=3, max=10, message="invalid name size")
	@Pattern(regexp="^[a-zA-Z]\\S*$", message="invalid name regex")
	private String name;
	@NotBlank(message="password should not be blank")
	private String password;
	private String website;
	private String bio;
	private String phone;
	private String gender;

	public User toEntity() {
		return User.builder()
				.password(password)
				.name(name)
				.website(website)
				.bio(bio)
				.phone(phone)
				.build();
	}
	
}
