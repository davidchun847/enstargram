<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<!--profile main-->
<main class="main">
	<!--profile section-->
	<section class="setting-container">
		<!--profile article-->
		<article class="setting__content">

			<!--profile id info-->
			<div class="content-item__01">
				<div class="item__img">
					<img src="#" onerror="this.src='/images/person.jpeg'" />
				</div>
				<div class="item__username">
					<h2>${principal.user.username}</h2>
				</div>
			</div>
			<!--profile id info end-->

			<!--profile modify form-->
			<form id="profileMod" onsubmit="updateUser(${principal.user.id}, event)">
				<div class="content-item__02">
					<div class="item__title">Name</div>
					<div class="item__input">
						<input type="text" name="name" placeholder="Name"
							value="${principal.user.name}" required="required"/>
					</div>
				</div>
				<div class="content-item__03">
					<div class="item__title">Username</div>
					<div class="item__input">
						<input type="text" name="username" placeholder="Username"
							value="${principal.user.username}" readonly="readonly" />
					</div>
				</div>
				<div class="content-item__04">
					<div class="item__title">Password</div>
					<div class="item__input">
						<input type="password" name="password" placeholder="Password" />
					</div>
				</div>
				<div class="content-item__05">
					<div class="item__title">Website</div>
					<div class="item__input">
						<input type="text" name="website" placeholder="Website"
							value="${principal.user.website}" />
					</div>
				</div>
				<div class="content-item__06">
					<div class="item__title">Bio</div>
					<div class="item__input">
						<textarea name="bio" id="" rows="3">${principal.user.bio}</textarea>
					</div>
				</div>
				<div class="content-item__07">
					<div class="item__title"></div>
					<div class="item__input">
						<span><b>Privacy</b></span> <span>Please enter your personal information. These are not disclosed to the others.</span>
					</div>
				</div>
				<div class="content-item__08">
					<div class="item__title">e-mail</div>
					<div class="item__input">
						<input type="text" name="email" placeholder="e-mail"
							value="${principal.user.email}" readonly="readonly" />
					</div>
				</div>
				<div class="content-item__09">
					<div class="item__title">phone</div>
					<div class="item__input">
						<input type="text" name="phone" placeholder="Phone"
							value="${principal.user.phone}" />
					</div>
				</div>
				<div class="content-item__10">
					<div class="item__title">gender</div>
					<div class="item__input">
						<input type="text" name="gender" value="${principal.user.gender}" />
					</div>
				</div>

				<!--submit button-->
				<div class="content-item__11">
					<div class="item__title"></div>
					<div class="item__input">
						<button>Submit</button>
					</div>
				</div>
				<!--submit button end-->

			</form>
			<!--profile modify form end-->
		</article>
	</section>
</main>

<script src="/js/profilemod.js"></script>

<%@ include file="../layout/footer.jsp"%>