package com.choco.enstargram.web.dto.subscribe;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
	private BigInteger userId;
	private String username;
	private String profileImgUrl;
	private Integer subscribeState;
	private Integer userSame;

}
