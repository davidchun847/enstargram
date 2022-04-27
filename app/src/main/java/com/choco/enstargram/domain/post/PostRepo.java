package com.choco.enstargram.domain.post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepo extends JpaRepository<Post, Long>{
	
	@Query(value="SELECT * FROM post WHERE (userId = :principalId) OR (userId IN (SELECT userToId FROM subscribe WHERE userFromId = :principalId)) ORDER BY id DESC", nativeQuery = true)
	Page<Post> getNews(Long principalId, Pageable pageable);
	
	@Query(value="SELECT post.* FROM post INNER JOIN (SELECT postId, COUNT(postId) AS numLikes FROM likes GROUP BY postId) AS likesView ON post.Id = likesView.postId ORDER BY numLikes DESC", nativeQuery=true)
	List<Post> getPopulars();

}
