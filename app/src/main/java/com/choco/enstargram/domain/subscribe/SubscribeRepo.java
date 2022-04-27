package com.choco.enstargram.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepo extends JpaRepository<Subscribe, Long> {
	
	@Modifying
	@Query(value="INSERT INTO subscribe(userFromId, userToId, createTime) VALUES(:userFromId, :userToId, now())", nativeQuery=true)
	void subscribe(Long userFromId, Long userToId);
	
	@Modifying
	@Query(value="DELETE FROM subscribe WHERE userFromId = :userFromId AND userToId = :userToId", nativeQuery=true)
	void unsubscribe(Long userFromId, Long userToId);
	
	@Query(value="SELECT COUNT(*) FROM subscribe WHERE userFromId=:principalId AND userToId=:pageUserId", nativeQuery=true)
	int subscribeState(Long principalId, Long pageUserId);
	
	@Query(value="SELECT COUNT(*) FROM subscribe WHERE userFromId=:pageUserId", nativeQuery=true)
	int numSubscribing(Long pageUserId);
}
