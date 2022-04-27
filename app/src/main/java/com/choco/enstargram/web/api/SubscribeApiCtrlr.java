package com.choco.enstargram.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choco.enstargram.config.auth.PrincipalDetails;
import com.choco.enstargram.service.SubscribeService;
import com.choco.enstargram.web.dto.CustomRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SubscribeApiCtrlr {
	
	private final SubscribeService subscribeService; 
	
	@PostMapping("/api/subscribe/{userToId}")
	public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long userToId) {
		subscribeService.subscribe(principalDetails.getUser().getId(), userToId);
		return new ResponseEntity<>(new CustomRespDto<>(1, "subscribe success", null), HttpStatus.OK);
	}
	
	@DeleteMapping("/api/subscribe/{userToId}")
	public ResponseEntity<?> unsubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long userToId) {
		subscribeService.unsubscribe(principalDetails.getUser().getId(), userToId);
		return new ResponseEntity<>(new CustomRespDto<>(1, "unsubscribe success", null), HttpStatus.OK);
	}

}
