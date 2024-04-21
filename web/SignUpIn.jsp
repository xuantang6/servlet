<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*, javax.sql.*" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="login2.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
        <style>

        </style>
        <title>Grocery Store</title> 
    </head>
    <body>
        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <form action="addUserServlet" method="post">
                    <h1>Create Account</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>or use your email for registration</span>
                    <input type="text" name="signup-username" placeholder="Username" required />
                    <input type="email" name="email" placeholder="Email" />
                    <div class="password-container">
                        <input type="password" name="signup-password" id="passwordInput1" placeholder="Password" />
                        <span class="password-toggle" onclick="togglePasswordVisibility('passwordInput1')">
                            <i class="fas fa-eye"></i>
                        </span>
                    </div>      
                    <button class="SignUpBtn">Sign Up</button>
                </form>
            </div>
            <div class="form-container sign-in-container">
                <form action="LoginServlet" method="post">
                    <h1>Sign in</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>or use your account</span>
                    <input type="text" name="signin-username" placeholder="Username" />
                    <div class="password-container">
                        <input type="password" name="signin-password" id="passwordInput2" placeholder="Password" />
                        <span class="password-toggle" onclick="togglePasswordVisibility('passwordInput2')">
                            <i class="fas fa-eye"></i>
                        </span>
                        <div>${tips}</div>
                    </div>
                    <a id="forgotPs" class="link-button" href="#">Forgot your password?</a>
                    <button class="SignInBtn">Sign In</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p>To keep connected with us please login with your personal info</p>
                        <button class="ghost" id="signIn">Sign In</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <p>Enter your personal details and start journey with us</p>
                        <button class="ghost" id="signUp">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- press forgot then hide create -->

        <footer>

        </footer>
        <script src="login2.js"></script>
    <body>
</html>