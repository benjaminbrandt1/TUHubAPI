<%@page import="job.UpdateJob"%>
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
    String titleKey = "title";
    String locationKey = "location";
    String hoursPerWeekKey = "hoursPerWeek";
    String startDateKey = "startDate";
    String descriptionKey = "description";
    String payKey = "pay";
    String isActiveKey = "isActive";
    Job job = new Job();
    Gson gson = new Gson();
    if(request.getParameter(jobIdKey) != null){
        job.setJobId(request.getParameter(jobIdKey));
    }
    if (request.getParameter(titleKey) != null) {
        job.setTitle(request.getParameter(titleKey));
    }
    if (request.getParameter(descriptionKey) != null) {
        job.setDescription(request.getParameter(descriptionKey));
    }
    if (request.getParameter(isActiveKey) != null) {
        job.setIsActive(request.getParameter(isActiveKey));
    }
    if (request.getParameter(payKey) != null) {
        job.setPay(request.getParameter(payKey));
    }   
    if (request.getParameter(locationKey) != null) {
        job.setLocation(request.getParameter(locationKey));
    } 
    if (request.getParameter(hoursPerWeekKey) != null) {
        int hpw = -1;
        try{
            hpw = Integer.parseInt(request.getParameter(hoursPerWeekKey));
            job.setHoursPerWeek(hpw);
        } catch (NumberFormatException e){
            job.setError(e.toString());
        }
        
    } 
    if (request.getParameter(startDateKey) != null) {
        job.setStartDate(request.getParameter(startDateKey));
    } 


    String jobId = job.getJobId();
    job.setJobId("");
    if(!job.isEmpty()){
        job.setJobId(jobId);
    
        if(job.getError().length() == 0){
        DbConn dbc = new DbConn();
        String msg = dbc.getErr(); // returns "" if connection is good, else error msg.
        if (msg.length() == 0) { // got open connection
            Job result = UpdateJob.update(dbc, job);
           
            out.print(gson.toJson(result).trim());
        } else {
            job.setError(msg);
            out.print(gson.toJson(job).trim());
        }
        dbc.close();
        } else {
            out.print(gson.toJson(job).trim());
        }
    } else {
        job.setError("No fields given to update");
        out.print(gson.toJson(job).trim());
    }
%>