<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Enstargram</title>
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
</head>

<body>
	<div class="container">
		<main class="loginMain">
			<!--user account register section-->
			<section class="login">
				<article class="login__form__container">
					<!--user account register from-->
					<div class="login__form">
						<!--logo-->
						<h1>
							<img src="/images/logo.jpg" alt="">
						</h1>
						<!--logo end-->

						<!--user account register input-->
						<!--Post to the DB the info-->
						<form class="login__input" action="/auth/signup" method="post">
							<input type="text" name="username" placeholder="Username"
								required="required" maxLength="10" />
							<input type="password"
								name="password" placeholder="Password" required="required" maxLength="20" />
							<input
								type="email" name="email" placeholder="e-mail"
								required="required" maxLength="20" />
							<input type="text" name="name"
								placeholder="Name" required="required" maxLength="20" />
							<button>Submit</button>
						</form>
						<!--user account register input ends-->
					</div>
					<!--user account register form ends-->

					<!--ask have an account-->
					<div class="login__register">
						<span>Do you have an account?</span> <a href="/auth/signin">Login</a>
					</div>
					<!--ask have an account ends-->

				</article>
			</section>
		</main>
	</div>
</body>

</html>