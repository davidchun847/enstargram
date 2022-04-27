<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enstargram</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
        integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
</head>

<body>
    <div class="container">
        <main class="loginMain">
        <!--login section-->
            <section class="login">
               <!--login box-->
                <article class="login__form__container">
                   <!--login form-->
                   <div class="login__form">
                        <h1><img src="/images/logo.jpg" alt=""></h1>
                        
                        <!--login input-->
                        <form class="login__input" action="/auth/signin" method="POST">
                            <input type="text" name="username" placeholder="Username" required="required" maxLength="10" />
                            <input type="password" name="password" placeholder="Password" required="required" maxLength="20" />
                            <button>Login</button>
                        </form>
                        <!--login input end-->
                        
                        <!-- or -->
                        <div class="login__horizon">
                            <div class="br"></div>
                            <div class="or">Or</div>
                            <div class="br"></div>
                        </div>
                        <!-- or end -->
                        
                        <!-- Oauth login -->
                        <div class="login__facebook">
                            <button onclick="javascript:location.href=`/oauth2/authorization/facebook`">
                                <i class="fab fa-facebook-square"></i>
                                <span>Login with Facebook</span>
                            </button>
                        </div>
                        <!-- Oauth login end -->
                    </div>
                    
                    <!--New to enstargram-->
                    <div class="login__register">
                        <span>New to Enstargram?</span>
                        <a href="/auth/signup">Create your account</a>
                    </div>
                    <!--New to enstargram end-->
                </article>
            </section>
        </main>
        
    </div>
</body>

</html>