package com.example.UserSystem.Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author dongyudeng
 */
@WebServlet(name = "LoginServlet", value = "/Login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String> map=new HashMap<>();
        BufferedReader reader=new BufferedReader(new FileReader("user.txt"));
        String user=null;
        while((user=reader.readLine())!=null){
            String regex=",";
            String[] users=user.split(regex);
            map.put(users[0],users[1]);
        }
        String userName=request.getParameter("userName"),passWord=request.getParameter("passWord");
        if(map.containsKey(userName)&&map.get(userName).equals(passWord)){
            request.getSession().setAttribute("userName",userName);
            response.sendRedirect("/UserSystem/hello.jsp");
        }
    }
}
