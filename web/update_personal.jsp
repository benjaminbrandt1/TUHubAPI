<%@page import="personal.UpdatePersonal"%>
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
    String personalIdKey = "personalId";
    String titleKey = "title";
    String locationKey = "location";
    String descriptionKey = "description";
    String isActiveKey = "isActive";
    Personal personal = new Personal();
    Gson gson = new Gson();
    if(request.getParameter(personalIdKey) != null){
        personal.setPersonalId(request.getParameter(personalIdKey));
    }
    if (request.getParameter(titleKey) != null) {
        personal.setTitle(request.getParameter(titleKey));
    }
    if (request.getParameter(descriptionKey) != null) {
        personal.setDescription(request.getParameter(descriptionKey));
    }
    if (request.getParameter(isActiveKey) != null) {
        personal.setIsActive(request.getParameter(isActiveKey));
    }
    if (request.getParameter(locationKey) != null) {
        personal.setLocation(request.getParameter(locationKey));
    }
    
    String personalId = personal.getPersonalId();
    personal.setPersonalId("");
    if(!personal.isEmpty()){
        personal.setPersonalId(personalId);

        DbConn dbc = new DbConn();
        String msg = dbc.getErr(); // returns "" if connection is good, else error msg.
        if (msg.length() == 0) { // got open connection
            Personal result = UpdatePersonal.update(dbc, personal);
           
            out.print(gson.toJson(result).trim());
        } else {
            personal.setError(msg);
            out.print(gson.toJson(personal).trim());
        }
        dbc.close();
    } else {
        personal.setError("No fields given to update");
        out.print(gson.toJson(personal).trim());
    }
%>