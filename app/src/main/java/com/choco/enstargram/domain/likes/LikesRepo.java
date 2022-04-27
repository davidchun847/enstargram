package com.choco.enstargram.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LikesRepo extends JpaRepository<Likes, Integer> {
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO likes(postId, userId, createTime) VALUES(:postId, :principalId, now())", nativeQuery=true)
	int likes(Long postId, Long principalId);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM likes WHERE postId = :postId AND userId = :principalId", nativeQuery=true)
	int dislikes(Long postId, Long principalId);
	

}
