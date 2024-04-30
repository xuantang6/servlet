
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

  <link rel="stylesheet" href="css/forgotPassword.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
  <style>
  
  </style>
  <title>Reset Password</title> 
</head>
<body>
  <div class="container" id="container">
    <div class="form-container sign-in-container">
      <form action="ResetPassword" method="post">
        <h1>Reset Password</h1>
        <input type="text" name="username" placeholder="Username" />
        <div class="password-container">
          <input type="email" name="email" id="email" placeholder="Email" />
          <input type="password" name="new_password" id="new_password" placeholder="New Password" />
          <span class="password-toggle" onclick="togglePasswordVisibility('new_password')">
            <i class="fas fa-eye"></i>
        </span>
          <input type="password" name="confirm_password" id="confirm_password" placeholder="Confirm Password" />
          ${psErrorInfo}

      </div>
        <button class="SignInBtn" >Reset</button>
      </form>
    </div>
  </div>
  <script src="js/login.js"></script>
  <body>
</html>