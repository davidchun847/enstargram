<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<!--popular post-->
<main class="popular">
	<div class="exploreContainer">

		<!--popular gallery-->
		<div class="popular-gallery">
			<c:forEach var="post" items="${posts}">
				<div class="p-img-box">
					<a href="/user/${post.user.id}"> <img src="/img/post/${post.imgUrl}" />
					</a>
				</div>
			</c:forEach>
		</div>
        <!--popular gallery end-->
	</div>
</main>
<!--popular post end-->

<%@ include file="../layout/footer.jsp"%>

