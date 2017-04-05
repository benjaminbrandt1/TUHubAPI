<%@page import="modelUser.*"%>
<%@page contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%> 

<%@page language="java" import="com.google.gson.*" %>
<%@page language="java" import="dbUtils.*" %>

<%
    /* 
    Note (in the 1st line of this file) that Web API JSP pages have a different contentType 
    (application/json). A JSP page would have contentType "text/html"

    The com.google.gson import line (line 3 above) references the GSON that should 
    have been added as a jar file to your project Libraries. 
    */
    String userIdKey = "userId";
    String id;
    if(request.getParameter(userIdKey) != null){
        id = request.getParameter(userIdKey);
    
     DbConn dbc = new DbConn();
            String msg = dbc.getErr(); // returns "" if connection is good, else error msg.
            if (msg.length() == 0) { // got open connection
    UserList userList = UserSearch.getUserById(dbc, id);

    Gson gson = new Gson();
    out.print(gson.toJson(userList).trim());
            }
            dbc.close();
    } else {
        id = "Error: Please enter a user ID.";
        Gson gson = new Gson();
        out.print(gson.toJson(id).trim());
    }
%>
