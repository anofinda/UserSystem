package com.example.UserSystem.Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;

/**
 * @author dongyudeng
 */
@WebServlet(name = "RegisterServlet", value = "/Register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/UserSystem",jdbcUser="user",jdbcPassWord="userpassword";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection connection= DriverManager.getConnection(jdbcUrl,jdbcUser,jdbcPassWord)){
            String userName=request.getParameter("userName"),passWord=request.getParameter("passWord"),gender=request.getParameter("gender");
            try(PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO users (name,passWord,gender) VALUES (?,?,?)")){
                preparedStatement.setObject(1,userName);
                preparedStatement.setObject(2,passWord);
                preparedStatement.setObject(3,gender);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/UserSystem/login.jsp");
    }
}
