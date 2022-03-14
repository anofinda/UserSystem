<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
    <!DOCTYPE html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h3>Login</h3><br>
        <form action="/UserSystem/Login" method="post">
            UserName:<input name="userName" type="text"><br>
            PassWord:<input name="passWord" type="password"><br>
            <input type="submit" value="login">
        </form>
    </body>
</html>