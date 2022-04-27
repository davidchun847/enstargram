package com.choco.enstargram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${file.img.base_dir}")
	private String imgFolder;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/img/**").addResourceLocations("file:///" + imgFolder)
				.setCachePeriod(60 * 10 * 6) // 1 hour
				.resourceChain(true).addResolver(new PathResourceResolver());
	}
}
