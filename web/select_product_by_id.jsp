<%@page import="product.ProductSearch"%>
<%@page import="product.Product"%>
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
    String productIdKey = "productId";
    String id;
    Gson gson = new Gson();

    if (request.getParameter(productIdKey) != null) {
        id = request.getParameter(productIdKey);

        try {
            int prodId = Integer.parseInt(id);
            DbConn dbc = new DbConn();
            String msg = dbc.getErr(); // returns "" if connection is good, else error msg.
            if (msg.length() == 0) { // got open connection
                Product product = ProductSearch.getProductById(dbc, prodId);
                out.print(gson.toJson(product).trim());
            } else {
                out.print(gson.toJson(msg).trim());
            }
            dbc.close();
        } catch (NumberFormatException e) {
            id = "Error: Please enter an integer for product ID";
            out.print(gson.toJson(id).trim());
        }
    } else {
        id = "Error: Please enter a product ID.";
        out.print(gson.toJson(id).trim());
    }
%>
