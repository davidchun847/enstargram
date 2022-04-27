package com.choco.enstargram.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepo extends JpaRepository<Comment, Long>{

	@Modifying
	@Query(value="INSERT INTO comment(content, postId, userId, createDate) VALUES(:content, :postId, :userId, now())", nativeQuery=true)
	Comment saveComment(String content, Long postId, Long userId);
}
