<%@page import="job.JobSearch"%>
<%@page import="job.Job"%>
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
    String jobIdKey = "jobId";
    String id;
    Gson gson = new Gson();

    if (request.getParameter(jobIdKey) != null) {
        id = request.getParameter(jobIdKey);

        try {
            int jobId = Integer.parseInt(id);
            DbConn dbc = new DbConn();
            String msg = dbc.getErr(); // returns "" if connection is good, else error msg.
            if (msg.length() == 0) { // got open connection
                Job job = JobSearch.getJobById(dbc, jobId);
                out.print(gson.toJson(job).trim());
            } else {
                out.print(gson.toJson(msg).trim());
            }
            dbc.close();
        } catch (NumberFormatException e) {
            id = "Error: Please enter an integer for job ID";
            out.print(gson.toJson(id).trim());
        }
    } else {
        id = "Error: Please enter a job ID.";
        out.print(gson.toJson(id).trim());
    }
%>
