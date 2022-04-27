<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<!--posting page-->
<main class="uploadContainer">
	<!--posting box-->
	<section class="upload">

		<!--posting top-->
		<div class="posting-top">
			<a href="home.html" class=""> <img src="/images/img_upload.jpg"
				alt="">
			</a>
			<p>Let's post!</p>
		</div>
		<!--posting top end-->

		<!--post form-->
		<form class="posting-form" action="/post" method="post"
			enctype="multipart/form-data">
			<input type="file" name="file" onchange="selectImg(this)" />
			<div class="upload-img">
				<img src="/images/person.jpeg" alt="" id="imageUploadPreview" />
			</div>

			<!--text, upload btn-->
			<div class="posting-form-detail">
				<input type="text" placeholder="Please type your story" name="text" />
				<button class="cta blue">Post</button>
			</div>
			<!--text, upload btn end-->

		</form>
		<!--post form end-->
	</section>
	<!--posting box end-->
</main>
<br />
<br />

<script src="/js/upload.js"></script>
<%@ include file="../layout/footer.jsp"%>