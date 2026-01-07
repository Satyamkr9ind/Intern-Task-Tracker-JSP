<%-- 
    Document   : login
    Created on : 30 Dec 2025, 1:33:33 pm
    Author     : satya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Intern Task Tracker - Login</title>

    <!-- CSS -->
    <link rel="stylesheet" href="stylesheet/login.css">

    <!-- JS -->
    <script src="script/validation.js"></script>
</head>
<body>

    <div class="login-box">
        <h2>Login</h2>

        <form action="login" method="post" onsubmit="return validateRole()">

            <input type="text" name="email" placeholder="Email" required>

            <input type="password" name="password" placeholder="Password" required>

            <div class="role-box">
                <label>
                    <input type="radio" name="role" value="supervisor"> Supervisor
                </label>
                <label>
                    <input type="radio" name="role" value="intern"> Intern
                </label>
            </div>

            <input type="submit" value="Login">

        </form>

        <div class="error">
            ${error}
        </div>

        <div class="footer-text">
            Internship Task Tracker
        </div>
    </div>

</body>
</html>
