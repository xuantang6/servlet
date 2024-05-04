<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*, javax.sql.*" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <link rel="stylesheet" href="css/login.css">

        <title>Grocery Store</title>
        <style>

        </style>
    </head>
    <body>
        <% String registerStatus = (String) request.getSession().getAttribute("registerStatus"); %>

        <% if (registerStatus != null && registerStatus.equals("registerSuccess")) { %>
        <script>
            $(document).ready(function() {
                $('#modalTour').modal('show');
            });
             <% session.removeAttribute("registerStatus"); %>
        </script>
        <% }%>
        
        ${psResetInfo}
        
         <!-- sign up success modal -->
        
        <div class="modal"tabindex="-1" role="dialog" data-bs-toggle="modal" id="modalTour" >
            <div class="modal-dialog" role="dialog">
                <div class="modal-content rounded-4 shadow">
                    <div class="modal-body p-5">
                        <h2 class="fw-bold mb-0">Congratulations!</h2>
                        <ul class="d-grid gap-4 my-5 list-unstyled small">
                            <li class="d-flex gap-4">
                                <i class="fa-solid fa-cart-shopping"></i>
                                <div>
                                    <h5 class="mb-0">Shop now</h5>
                                    Thank you for choosing to join GreenBasket!
                                    <div>Your registration has been successfully completed.</div>
                                </div>
                            </li>
                        </ul>
                        <ul class="d-grid gap-4 my-5 list-unstyled small">
                            <li class="d-flex gap-4">
                                <i class="fa-solid fa-seedling"></i>
                                <div>
                                    <h5 class="mb-0">Fresh</h5>
                                    We are committed to providing you with the freshest products, each item is carefully selected and preserved.
                                </div>
                            </li>
                        </ul>
                        <ul class="d-grid gap-4 my-5 list-unstyled small">
                            <li class="d-flex gap-4">
                                <i class="fa-solid fa-tags"></i>
                                <div>
                                    <h5 class="mb-0">Price Guarantee</h5>
                                    We are promised to offer a lowest price guarantee for your shopping peace of mind!
                                </div>
                            </li>
                        </ul>
                        <button type="button" class="btn btn-lg btn-primary mt-5 w-100" data-bs-dismiss="modal">Great, Sign in now!</button>
                    </div>
                </div>
            </div>
        </div>

        
        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <form action="RegisterServlet"  method="post">
                    <h1>Create Account</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>or use your email for registration</span>
                    <input type="text" id="usernameInput" name="signup-username" placeholder="Username" required />
                    <i id="checkIcon" class="fas fa-check-circle"></i>
                    <i id="unCheckIcon" class="fas fa-circle-xmark"></i>

                    <input type="email" name="email" placeholder="Email" />
                    
                    <div class="password-container">
                        <input type="password" name="signup-password" id="passwordInput1" placeholder="Password" />
                        <span class="password-toggle" onclick="togglePasswordVisibility('passwordInput1')">
                            <i class="fas fa-eye"></i>
                        </span>
                    </div>
                    
                    <div id="passwordWarning" style="color: red;"></div>
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
                    <input type="text" name="signin-username" required placeholder="Username" />
                    <div class="password-container">
                        <input type="password" name="signin-password" required id="passwordInput2" placeholder="Password" />
                        <span class="password-toggle" onclick="togglePasswordVisibility('passwordInput2')">
                            <i class="fas fa-eye"></i>
                        </span>
                    </div>
                    ${tips}
                    <div class="g-recaptcha" data-sitekey="6LdvrcYpAAAAAGppAr6yc0uwfXxM4uy3VpCtvb36" data-callback="recaptchaCallback"></div>
                    <a id="forgotPs" class="link-button" href="forgotPassword.jsp">Forgot your password?</a>
                    <span><button id="signInBtn" disabled class="SignInBtn">Sign In</button></span>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p> Let's make your shopping experience even better.</p>
                        <button class="ghost" id="signIn">Sign In</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <p>Ready to stock up your kitchen with the best ingredients?</p>
                        <button class="ghost" id="signUp">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>
       
        <footer>
        </footer>
        <script>
            
        </script>
        
        <script src="js/login.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

    <body>
</html>