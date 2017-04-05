<%@page import="product.InsertProduct"%>
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
    String productIdKey = "productID";
    String titleKey = "title";
    String descriptionKey = "description";
    String priceKey = "price";
    String isActiveKey = "isActive";
    String ownerIdKey = "ownerId";
    Product product = new Product();
    Gson gson = new Gson();
    
    if (request.getParameter(productIdKey) != null) {
        product.setProductId(request.getParameter(productIdKey));
    }
    if (request.getParameter(titleKey) != null) {
        product.setTitle(request.getParameter(titleKey));
    }
    if (request.getParameter(descriptionKey) != null) {
        product.setDescription(request.getParameter(descriptionKey));
    }
    if (request.getParameter(priceKey) != null) {
        product.setPrice(request.getParameter(priceKey));
    }
    if (request.getParameter(isActiveKey) != null) {
        product.setIsActive(request.getParameter(isActiveKey));
    }
    if (request.getParameter(ownerIdKey) != null) {
        product.setOwnerId(request.getParameter(ownerIdKey));
    }

    Product error = InsertProduct.validate(product);

    if (error.isEmpty()) {

        DbConn dbc = new DbConn();
        String msg = dbc.getErr(); // returns "" if connection is good, else error msg.
        if (msg.length() == 0) { // got open connection
            Product result = InsertProduct.insert(product, dbc);
           
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