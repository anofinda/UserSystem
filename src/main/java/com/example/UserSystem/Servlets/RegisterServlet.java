package com.example.UserSystem.Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

/**
 * @author dongyudeng
 */
@WebServlet(name = "RegisterServlet", value = "/Register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer=new PrintWriter("user.txt");
        String userName=request.getParameter("userName"),passWord=request.getParameter("passWord"),gender=request.getParameter("gender");
        writer.println(userName + "," + passWord + "," + gender);
        response.sendRedirect("/UserSystem/login.jsp");
    }
}
