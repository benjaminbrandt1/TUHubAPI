<%@page import="product.ProductList"%>
<%@page import="product.ProductSearch"%>
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
    String title = "";
    String startKey = "offset";
    String numRowsKey = "limit";
    String numRows = "1000000";
    String start = "0";
    Gson gson = new Gson();

    if (request.getParameter(titleKey) != null) {
        title = request.getParameter(titleKey);
    }
    if (request.getParameter(startKey) != null && request.getParameter(startKey).length() != 0) {
        start = request.getParameter(startKey);
    }
    if (request.getParameter(numRowsKey) != null && request.getParameter(numRowsKey).length() != 0) {
        numRows = request.getParameter(numRowsKey);
    }
        
            DbConn dbc = new DbConn();
            String msg = dbc.getErr(); // returns "" if connection is good, else error msg.
            if (msg.length() == 0) { // got open connection
                ProductList productList = ProductSearch.searchActiveProductTitles(dbc, title, start, numRows);
                out.print(gson.toJson(productList).trim());
            } else {
                out.print(gson.toJson(msg).trim());
            }
            dbc.close();


%>
