<%@page import="personal.InsertPersonal"%>
<%@page import="personal.Personal"%>
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
    String titleKey = "title";
    String locationKey = "location";
    String descriptionKey = "description";
    String isActiveKey = "isActive";
    String ownerIdKey = "ownerId";
    Personal personal = new Personal();
    Gson gson = new Gson();
    
    if (request.getParameter(titleKey) != null) {
        personal.setTitle(request.getParameter(titleKey));
    }
    if (request.getParameter(descriptionKey) != null) {
        personal.setDescription(request.getParameter(descriptionKey));
    }
    if (request.getParameter(isActiveKey) != null) {
        personal.setIsActive(request.getParameter(isActiveKey));
    }
    if (request.getParameter(ownerIdKey) != null) {
        personal.setOwnerId(request.getParameter(ownerIdKey));
    }
    if (request.getParameter(locationKey) != null) {
        personal.setLocation(request.getParameter(locationKey));
    }   

    Personal error = InsertPersonal.validate(personal);

    if (error.isEmpty()) {

        DbConn dbc = new DbConn();
        String msg = dbc.getErr(); // returns "" if connection is good, else error msg.
        if (msg.length() == 0) { // got open connection
            Personal result = InsertPersonal.insert(personal, dbc);
           
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