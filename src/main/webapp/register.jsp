<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
    <!DOCTYPE html>
    <head>
        <meta charset="UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h3>Register</h3><br>
        <form action="/UserSystem/Register" method="post">
            UserName:<input name="userName" type="text"><br>
            PassWord:<input name="passWord" type="password"><br>
            Gender:
            <select id="gender" name="gender">
                <option value="male">male</option>
                <option value="female">female</option>
            </select><br>
            <input type="submit" value="register">
        </form>
    </body>
</html>