<%@page import="personal.PersonalSearch"%>
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
    String id;
    Gson gson = new Gson();

    if (request.getParameter(personalIdKey) != null) {
        id = request.getParameter(personalIdKey);

        try {
            int personalId = Integer.parseInt(id);
            DbConn dbc = new DbConn();
            String msg = dbc.getErr(); // returns "" if connection is good, else error msg.
            if (msg.length() == 0) { // got open connection
                Personal personal = PersonalSearch.getPersonalById(dbc, personalId);
                out.print(gson.toJson(personal).trim());
            } else {
                out.print(gson.toJson(msg).trim());
            }
            dbc.close();
        } catch (NumberFormatException e) {
            id = "Error: Please enter an integer for personal ID";
            out.print(gson.toJson(id).trim());
        }
    } else {
        id = "Error: Please enter a personal ID.";
        out.print(gson.toJson(id).trim());
    }
%>
