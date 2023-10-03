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
            <h1>Change User Credentials</h1>
            
    <form method="post" action="AdmUpdateCredentials">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="currentPassword">Current Password:</label>
        <input type="password" id="currentPassword" name="currentPassword" required><br><br>

        <label for="newPassword">New Password:</label>
        <input type="password" id="newPassword" name="newPassword" required><br><br>

        <input type="submit" value="Update Credentials">
    </form>
		</div>
     </div>
</body>
<style>
    html, body{
        background-color: #a1eba0;
    }
</style>
</html>