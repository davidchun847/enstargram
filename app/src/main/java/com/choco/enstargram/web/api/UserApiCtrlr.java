package com.choco.enstargram.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.choco.enstargram.config.auth.PrincipalDetails;
import com.choco.enstargram.domain.user.User;
import com.choco.enstargram.service.SubscribeService;
import com.choco.enstargram.service.UserService;
import com.choco.enstargram.web.dto.CustomRespDto;
import com.choco.enstargram.web.dto.subscribe.SubscribeDto;
import com.choco.enstargram.web.dto.user.UserUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiCtrlr {

	private final UserService userUpdateService;
	private final SubscribeService subscribeService;

	@PutMapping("/api/user/{principalId}/profile-img-url")
	public ResponseEntity<?> updateProfileImgUrl(@PathVariable Long principalId, MultipartFile profileImageFile, @AuthenticationPrincipal PrincipalDetails principalDetails) {
//		arg profileImageFile should be same as in jsp file
		User user = userUpdateService.updateProfileImgUrl(principalId, profileImageFile);
		principalDetails.setUser(user);
		return new ResponseEntity<>(new CustomRespDto<>(1, "update profile img success", null), HttpStatus.OK);
	}
	
	@GetMapping("/api/user/{pageUserId}/subscribe")
	public ResponseEntity<?> subscribeList(@PathVariable Long pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
//		System.out.println(pageUserId);
//		System.out.println(pageUserId.getClass());
//		Long pageUserId_l = Long.parseLong(pageUserId);
		List<SubscribeDto> subscribeDtos = subscribeService.getSubscribeList(principalDetails.getUser().getId(), pageUserId);
		return new ResponseEntity<>(new CustomRespDto<>(1, "subscibing list returned", subscribeDtos), HttpStatus.OK);
	}
	
	@PutMapping("/api/user/{id}")
	public CustomRespDto<?> updateUser(
			@PathVariable Long id, 
			@Valid UserUpdateRequestDto reqDto,
			BindingResult bindRes, // should be next to dto
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
			User userEntity = userUpdateService.updateUser(id, reqDto.toEntity());
			principalDetails.setUser(userEntity);
			return new CustomRespDto<>(1, "User update success", userEntity);
	}
}
