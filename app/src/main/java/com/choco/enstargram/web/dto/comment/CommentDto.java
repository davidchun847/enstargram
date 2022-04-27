package com.choco.enstargram.web.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentDto {
	@NotBlank // null check empty check space check
	private String content;
	@NotNull // null check
	private Long postId;

}
