package com.choco.enstargram.config.oauth;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.choco.enstargram.config.auth.PrincipalDetails;
import com.choco.enstargram.domain.user.User;
import com.choco.enstargram.domain.user.UserRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService {

	private final UserRepo userRepo;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		OAuth2User oauth2User = super.loadUser(userRequest);

		Map<String, Object> userInfo = oauth2User.getAttributes();
		String username = (String) "facebook_" + userInfo.get("id");
		String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
		String email = (String) userInfo.get("email");
		String name = (String) userInfo.get("name");

		User user = userRepo.findByUsername(username);
		if (user == null) { // user not exist
			user = User.builder().username(username).password(password).email(email).name(name).role("ROLE_USER").build();
			return new PrincipalDetails(userRepo.save(user), oauth2User.getAttributes());
		} else {
			return new PrincipalDetails(user);
		}
	}
}
