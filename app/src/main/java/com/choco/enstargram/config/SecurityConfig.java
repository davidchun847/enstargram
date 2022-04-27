package com.choco.enstargram.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.choco.enstargram.config.oauth.OAuth2DetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration // makes singleton class to create a single bean
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private ArrayList<String> authAddrs =
			new ArrayList<>(Arrays.asList(
					"/", "/user/**", "/post/**", "/subscribe/**", "/comment/**", "/api/**"));
	
	private final OAuth2DetailsService oAuth2DetailsService;
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(http);
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers(this.authAddrs.toArray(String[]::new)).authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin() // GET
			.loginPage("/auth/signin") // POST
			.defaultSuccessUrl("/")
			.and()
			.oauth2Login() // also allow oauth login
			.userInfoEndpoint() // get account info
			.userService(oAuth2DetailsService);
		
	}
}
