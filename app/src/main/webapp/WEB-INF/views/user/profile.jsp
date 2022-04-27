<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<!--profile section-->
<section class="profile">
	<!--user info container-->
	<div class="profileContainer">
		<!--user image-->
		<div class="profile-left">
			<div class="profile-img-wrap story-border"
				onclick="popup('.modal-image')">
				<form id="userProfileImageForm">
					<input type="file" name="profileImageFile" style="display: none"
						id="userProfileImageInput" />
				</form>

				<img class="profile-image"
					src="/img/profile/${profileDto.user.profileImgUrl}"
					onerror="this.src='/images/person.jpeg'" id="userProfileImage" />
			</div>
		</div>
		<!--user image end-->

		<!--user info, upload image, subscribe-->
		<div class="profile-right">
			<div class="name-group">
				<h2>${profileDto.user.name}</h2>
				<c:choose>
					<c:when test="${profileDto.pageOwnerSame}">
						<button class="cta" onclick="location.href='/post/upload'">
							Post</button>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${profileDto.subscribeState}">
								<button class="cta blue"
									onclick="toggleSubscribe(${profileDto.user.id}, this)">
									Unsubscribe</button>
							</c:when>
							<c:otherwise>
								<button class="cta"
									onclick="toggleSubscribe(${profileDto.user.id}, this)">
									Subscribe</button>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>

				<button class="modi" onclick="popup('.modal-info')">
					<i class="fas fa-cog"></i>
				</button>
			</div>

			<div class="subscribe">
				<ul>
					<li><a href="">Posts<span>${profileDto.numPosts}</span>
					</a></li>
					<li><a
						href="javascript:openSubscribingModal(${profileDto.user.id});">
							Subscribing<span>${profileDto.numSubscribing}</span>
					</a></li>
				</ul>
			</div>
			<div class="state">
				<h4>${profileDto.user.bio}</h4>
				<h4>${profileDto.user.website}</h4>
			</div>
		</div>
		<!--user info, upload image, subscribe-->
	</div>
</section>

<!--posts section-->
<section id="tab-content">
	<!--posts continaer-->
	<div class="profileContainer">
		<!---->
		<div id="tab-1-content" class="tab-content-item show">
			<!--posts grid-->
			<div class="tab-1-content-inner">
				<!--items-->

				<c:forEach var="post" items="${profileDto.user.posts}">
					<div class="img-box">
						<a href=""> <img src="/img/post/${post.imgUrl}" />
						</a>
						<div class="comment">
							<a href="#" class=""> <i class="fas fa-heart"></i><span>${post.numLikes}</span>
							</a>
						</div>
					</div>
				</c:forEach>

				<!--items end-->
			</div>
		</div>
	</div>
</section>

<!--logout, my account modify modal-->
<div class="modal-info" onclick="modalInfo()">
	<div class="modal">
		<button onclick="location.href='/user/1/profilemod'">My
			profile</button>
		<button onclick="location.href='/logout'">Logout</button>
		<button onclick="closePopup('.modal-info')">Cancel</button>
	</div>
</div>
<!--logout, my account modify modal end-->

<!--update profile img modal-->
<div class="modal-image" onclick="modalImage()">
	<div class="modal">
		<p>Show your new profile photo</p>
		<button
			onclick="profileImageUpload(${profileDto.user.id}, ${principal.user.id})">Upload</button>
		<button onclick="closePopup('.modal-image')">Cancel</button>
	</div>
</div>

<!--update profile img end-->

<div class="modal-subscribe">
	<div class="subscribe">
		<div class="subscribe-header">
			<span>Subscribing</span>
			<button onclick="modalClose()">
				<i class="fas fa-times"></i>
			</button>
		</div>
		<div class="subscribe-list" id="subscribeModalList"></div>
	</div>
</div>

<script src="/js/profile.js"></script>

<%@ include file="../layout/footer.jsp"%>
