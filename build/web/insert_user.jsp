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
    String userIdKey = "TUID";
    String emailKey = "email";
    String firstNameKey = "firstName";
    String lastNameKey = "lastName";
    String phoneNumberKey = "phoneNumber";
    User user = new User();
    Gson gson = new Gson();

    if (request.getParameter(userIdKey) != null) {
        user.setTuId(request.getParameter(userIdKey));
    }
    if (request.getParameter(emailKey) != null) {
        user.setEmail(request.getParameter(emailKey));
    }
    if (request.getParameter(firstNameKey) != null) {
        user.setFirstName(request.getParameter(firstNameKey));
    }
    if (request.getParameter(lastNameKey) != null) {
        user.setLastName(request.getParameter(lastNameKey));
    }
    if (request.getParameter(phoneNumberKey) != null) {
        user.setPhoneNumber(request.getParameter(phoneNumberKey));
    }

    User error = InsertUser.validate(user);

    if (error.isEmpty()) {

        DbConn dbc = new DbConn();
        String msg = dbc.getErr(); // returns "" if connection is good, else error msg.
        if (msg.length() == 0) { // got open connection
            User result = InsertUser.insert(user, dbc);

            out.print(gson.toJson(result).trim());
        } else {
            out.print(gson.toJson(msg).trim());
        }
        dbc.close();
    } else {
        error.setError("Insert Failed");
        out.print(gson.toJson(error).trim());
    }
%>