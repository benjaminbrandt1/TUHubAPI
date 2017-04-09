<%@page import="job.InsertJob"%>
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
    String jobId = "jobId";
    String titleKey = "title";
    String locationKey = "location";
    String hoursPerWeekKey = "hoursPerWeek";
    String startDateKey = "startDate";
    String descriptionKey = "description";
    String payKey = "pay";
    String isActiveKey = "isActive";
    String ownerIdKey = "ownerId";
    Job job = new Job();
    Gson gson = new Gson();
    
    if (request.getParameter(jobId) != null) {
        job.setJobId(request.getParameter(jobId));
    }
    if (request.getParameter(titleKey) != null) {
        job.setTitle(request.getParameter(titleKey));
    }
    if (request.getParameter(descriptionKey) != null) {
        job.setDescription(request.getParameter(descriptionKey));
    }
    if (request.getParameter(payKey) != null) {
        job.setPay(request.getParameter(payKey));
    }
    if (request.getParameter(isActiveKey) != null) {
        job.setIsActive(request.getParameter(isActiveKey));
    }
    if (request.getParameter(ownerIdKey) != null) {
        job.setOwnerId(request.getParameter(ownerIdKey));
    }
    if (request.getParameter(locationKey) != null) {
        job.setLocation(request.getParameter(locationKey));
    }
    if (request.getParameter(hoursPerWeekKey) != null) {
        int hours = Integer.parseInt(request.getParameter(hoursPerWeekKey));
        job.setHoursPerWeek(hours);
    }
    if (request.getParameter(startDateKey) != null) {
        job.setStartDate(request.getParameter(startDateKey));
    }
    

    Job error = InsertJob.validate(job);

    if (error.isEmpty()) {

        DbConn dbc = new DbConn();
        String msg = dbc.getErr(); // returns "" if connection is good, else error msg.
        if (msg.length() == 0) { // got open connection
            Job result = InsertJob.insert(job, dbc);
           
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