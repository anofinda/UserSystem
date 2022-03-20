<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
    <%
        String userName= (String) request.getSession().getAttribute("userName");
        if(userName==null)userName="guest";
        out.println("hello,"+userName+"!");
    %>
</html>