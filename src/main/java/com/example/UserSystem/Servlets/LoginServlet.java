package com.example.UserSystem.Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

/**
 * @author dongyudeng
 */
@WebServlet(name = "LoginServlet", value = "/Login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/UserSystem",jdbcUser="user",jdbcPassWord="userpassword";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection connection=DriverManager.getConnection(jdbcUrl,jdbcUser,jdbcPassWord)){
            String userName=request.getParameter("userName"),passWord=request.getParameter("passWord");
            try(Statement statement=connection.createStatement()){
                try(ResultSet resultSet=statement.executeQuery("SELECT passWord FROM users WHERE name='"+userName+"'")){
                    String expectedPassWord = null;
                    if(resultSet.next()){
                        expectedPassWord=resultSet.getString(1);
                    }
                    if(expectedPassWord!=null&&passWord.equals(expectedPassWord)){
                        request.getSession().setAttribute("userName",userName);
                        response.sendRedirect("/UserSystem/hello.jsp");
                    }
                    else {
                        request.setAttribute("flag",true);
                        response.sendRedirect("/UserSystem/login.jsp");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("flag",true);
            response.sendRedirect("/UserSystem/login.jsp");
        }
    }
}
