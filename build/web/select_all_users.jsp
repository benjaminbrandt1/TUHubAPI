<%@page import="modelUser.*"%>
<%@page contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%> 

<%@page language="java" import="com.google.gson.*" %>
<%@page language="java" import="dbUtils.*" %>

<%  
        String startKey = "offset";
    String numRowsKey = "limit";
    String numRows = "1000000";
    String start = "0";
    
    if (request.getParameter(startKey) != null && request.getParameter(startKey).length() != 0) {
        start = request.getParameter(startKey);
    }
    if (request.getParameter(numRowsKey) != null && request.getParameter(numRowsKey).length() != 0) {
        numRows = request.getParameter(numRowsKey);
    }
    
     DbConn dbc = new DbConn();
            String msg = dbc.getErr(); // returns "" if connection is good, else error msg.
            if (msg.length() == 0) { // got open connection
    UserList personList = UserSearch.getAllUsersList(dbc, start, numRows);

    Gson gson = new Gson();
    out.print(gson.toJson(personList).trim());
            }
            dbc.close();
%>