package com.example.UserSystem.Servlets;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

/**
 * @author dongyudeng
 */
@WebServlet(name = "LoginServlet", value = "/Login")
public class LoginServlet extends HttpServlet {
    static private final String jdbcUrl = "jdbc:mysql://localhost:3306/UserSystem";
    static private final String jdbcUser="user";
    static private final String jdbcPassWord="userpassword";
    static private final HikariConfig hikariConfig=new HikariConfig();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        initConfig();
        DataSource dataSource=new HikariDataSource(hikariConfig);
        try(Connection connection=dataSource.getConnection()){
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
    static private void initConfig(){
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(jdbcUser);
        hikariConfig.setPassword(jdbcPassWord);
        hikariConfig.addDataSourceProperty("connectionTimeout","1000");
        hikariConfig.addDataSourceProperty("idleTimeout","60000");
        hikariConfig.addDataSourceProperty("maximumPoolSize","10");
    }
}
