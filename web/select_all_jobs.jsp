<%@page import="job.JobSearch"%>
<%@page import="job.JobList"%>
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
    boolean activeOnly = true;
    String activeOnlyKey = "activeOnly";
    String error = "";
    String startKey = "offset";
    String numRowsKey = "limit";
    String numRows = "1000000";
    String start = "0";
    Gson gson = new Gson();
    
    if (request.getParameter(startKey) != null && request.getParameter(startKey).length() != 0) {
        start = request.getParameter(startKey);
    }
    if (request.getParameter(numRowsKey) != null && request.getParameter(numRowsKey).length() != 0) {
        numRows = request.getParameter(numRowsKey);
    }

    if (request.getParameter(activeOnlyKey) != null) {
        if (request.getParameter(activeOnlyKey).equalsIgnoreCase("false")) {
            activeOnly = false;
        } else if (request.getParameter(activeOnlyKey).equalsIgnoreCase("true")) {
            activeOnly = true;
        } else {
            error = "Invalid argument for " + activeOnlyKey + "  parameter.";
        }
    } else {
        error = "Invalid argument for " + activeOnlyKey + "  parameter.";
    }

    if (error.length() == 0) {
        DbConn dbc = new DbConn();
        error = dbc.getErr(); // returns "" if connection is good, else error msg.
        if (error.length() == 0) { // got open connection
            JobList jobList = JobSearch.getAllJobList(dbc, activeOnly, start, numRows);

            out.print(gson.toJson(jobList).trim());
        } else {
            out.print(gson.toJson(error).trim());
        }
        dbc.close();
    } else {
        out.print(gson.toJson(error).trim());
    }
%>