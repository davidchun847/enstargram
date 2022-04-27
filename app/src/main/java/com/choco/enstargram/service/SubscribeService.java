package com.choco.enstargram.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choco.enstargram.domain.subscribe.SubscribeRepo;
import com.choco.enstargram.handler.exception.CustomApiException;
import com.choco.enstargram.web.dto.subscribe.SubscribeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {
	
	private final SubscribeRepo subscribeRepo;
	private final EntityManager em;
	
	@Transactional
	public void subscribe(Long userFromId, Long userToId) {
		if (userFromId == userToId) {
			throw new CustomApiException("self-subscribe is not allowed");
		} else {
			try {
				subscribeRepo.subscribe(userFromId, userToId);
			} catch (Exception e) {
				throw new CustomApiException("already subscribed");
			}
		}
	}
	
	@Transactional
	public void unsubscribe(Long userFromId, Long userToId) {
		if (userFromId == userToId) {
			throw new CustomApiException("self-unsubscribe is not allowed");
		} else {
			try {
				subscribeRepo.unsubscribe(userFromId, userToId);
			} catch (Exception e) {
				throw new CustomApiException("unscribe err");
			}
		}
	}
	
	@Transactional(readOnly=true)
	public List<SubscribeDto> getSubscribeList(Long principalId, Long pageUserId) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT u.id, u.username, u.profileImgUrl, ");
		sb.append("if ((SELECT 1 FROM subscribe WHERE userFromId = ? AND userToId = u.id), 1, 0) subscribeState, ");
		sb.append("if ((?=u.id), 1, 0) userSame ");
		sb.append("FROM user u INNER JOIN subscribe s ");
		sb.append("ON u.id = s.userToId ");
		sb.append("WHERE s.userFromId = ?");
		
		Query query = em.createNativeQuery(sb.toString())
				.setParameter(1, principalId)
				.setParameter(2, principalId)
				.setParameter(3, pageUserId);
		
		JpaResultMapper res_query = new JpaResultMapper();
		List<SubscribeDto> subscribeDtos =  res_query.list(query, SubscribeDto.class);
		return subscribeDtos;
	}
}
