package com.choco.enstargram.web.dto.user;

import com.choco.enstargram.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
	private boolean pageOwnerSame;
	private int numPosts;
	private boolean subscribeState;
	private int numSubscribing;
	private User user;

}
