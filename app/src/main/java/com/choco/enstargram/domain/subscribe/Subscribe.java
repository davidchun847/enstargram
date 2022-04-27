package com.choco.enstargram.domain.subscribe;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.choco.enstargram.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "subscribe_uk", columnNames = {"userFromId", "userToId"}) })
public class Subscribe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "userFromId")
	@ManyToOne
	private User userFrom;
	@JoinColumn(name = "userToId")
	@ManyToOne
	private User userTo;

	private LocalDateTime createTime;

	@PrePersist
	public void createTimeNow() {
		this.createTime = LocalDateTime.now();
	}
}
