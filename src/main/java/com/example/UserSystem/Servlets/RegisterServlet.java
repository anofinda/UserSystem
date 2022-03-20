package com.example.UserSystem.Servlets;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.*;
import java.sql.*;

/**
 * @author dongyudeng
 */
@WebServlet(name = "RegisterServlet", value = "/Register")
public class RegisterServlet extends HttpServlet {
    static private final String jdbcUrl = "jdbc:mysql://localhost:3306/UserSystem";
    static private final String jdbcUser = "user";
    static private final String jdbcPassWord = "userpassword";
    static private final HikariConfig hikariConfig = new HikariConfig();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        initConfig();
        DataSource dataSource = new HikariDataSource(hikariConfig);
        try (Connection connection = dataSource.getConnection()) {
            String userName = request.getParameter("userName"), passWord = request.getParameter("passWord"), gender = request.getParameter("gender");
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name,passWord,gender) VALUES (?,?,?)")) {
                preparedStatement.setObject(1, userName);
                preparedStatement.setObject(2, passWord);
                preparedStatement.setObject(3, gender);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/UserSystem/login.jsp");
    }

    static private void initConfig() {
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(jdbcUser);
        hikariConfig.setPassword(jdbcPassWord);
        hikariConfig.addDataSourceProperty("connectionTimeout", "1000");
        hikariConfig.addDataSourceProperty("idleTimeout", "60000");
        hikariConfig.addDataSourceProperty("maximumPoolSize", "10");
    }
}
