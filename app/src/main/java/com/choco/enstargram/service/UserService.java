package com.choco.enstargram.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.choco.enstargram.domain.subscribe.SubscribeRepo;
import com.choco.enstargram.domain.user.User;
import com.choco.enstargram.domain.user.UserRepo;
import com.choco.enstargram.handler.exception.CustomApiException;
import com.choco.enstargram.handler.exception.CustomException;
import com.choco.enstargram.handler.exception.CustomValidApiException;
import com.choco.enstargram.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepo userRepo;
	private final SubscribeRepo subscribeRepo;
	private final BCryptPasswordEncoder passwordEncoder;
	@Value("${file.img.profile_dir}")
	private String profileImgDir;
	
	@Transactional(readOnly=true)
	public UserProfileDto getProfile(Long pageUserId, Long principalId) {
		UserProfileDto userProfileDto = new UserProfileDto();
		
		User user = userRepo.findById(pageUserId).orElseThrow(() -> {
			throw new CustomException("profile: user id not found");
		});
		
		userProfileDto.setUser(user);
		userProfileDto.setPageOwnerSame(pageUserId == principalId);
		userProfileDto.setNumPosts(user.getPosts().size());
		
		int subscribeState = subscribeRepo.subscribeState(principalId, pageUserId);
		int numSubscribing = subscribeRepo.numSubscribing(pageUserId);
		
		userProfileDto.setSubscribeState(subscribeState == 1);
		userProfileDto.setNumSubscribing(numSubscribing);
		
		user.getPosts().forEach((post)->{
			post.setNumLikes(post.getLikes().size());
		});
		
		return userProfileDto;
	}

	@Transactional
	public User updateUser(Long userId, User user) {
		User userEntity = userRepo.findById(userId).orElseThrow(() -> {
			return new CustomValidApiException("The id not found");
		});

		String rawPw = user.getPassword();
		String encPw = passwordEncoder.encode(rawPw);

		userEntity.setName(user.getName());
		userEntity.setPassword(encPw);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		return userEntity;
	}
	
	@Transactional
	public User updateProfileImgUrl(Long principalId, MultipartFile profileImgFile) {
		UUID uuid = UUID.randomUUID();
		String imgFileName = uuid + "_" + profileImgFile.getOriginalFilename();
		System.out.println("img file name:" + imgFileName);
		Path imgFilePath = Paths.get(profileImgDir + imgFileName);
		
		try {
			Files.write(imgFilePath, profileImgFile.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		User user = userRepo.findById(principalId).orElseThrow(()->{
			throw new CustomApiException("cannot find user");
		});
		
		user.setProfileImgUrl(imgFileName);
		
		return user;
	} // update by dirty check

}
