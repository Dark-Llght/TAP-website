<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">   
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GameOn performance booster</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
    <div class="mainLogin">
        <div class="login">
            <h2>Register New User</h2>
            <form action="RegisterServlet" method="post">
			  <label for="name">Name:</label>
              <input type="text" id="name" name="name" required>
              <label for="email">E-mail:</label>
              <input type="email" id="email" name="email" required>
              <label for="password">Password:</label>
              <input type="password" id="pass" name="password" required>
              <input type="submit" value="submit">
            </form>
		</div>
     </div>
</body>
<style>
    html, body{
        background-color: #161616;
    }
</style>
</html>