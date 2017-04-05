<%@page import="modelUser.*"%>
<%@page contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%> 

<%@page language="java" import="com.google.gson.*" %>
<%@page language="java" import="dbUtils.*" %>

<%

    String userIdKey = "userId";
    String phoneNumberKey = "phoneNumber";
    String id = "";
    String phoneNumber = "";
    String error = "";
    String regexStr = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";
    if (request.getParameter(userIdKey) != null) {
        id = request.getParameter(userIdKey);

        if (request.getParameter(phoneNumberKey) != null) {
            phoneNumber = request.getParameter(phoneNumberKey);
            if (phoneNumber.length() > 0 && !phoneNumber.matches(regexStr)) {
                error = "Invalid format for Phone Number";
            } else {
                phoneNumber = User.removeDashes(phoneNumber);
            }
        }
    } else {
        error = "Error: Please enter a user ID.";
    }

    if (error.length() == 0) {
        DbConn dbc = new DbConn();
        String msg = dbc.getErr(); // returns "" if connection is good, else error msg.
        if (msg.length() == 0) { // got open connection
            String res = UpdateUser.updatePhoneNumber(dbc, id, phoneNumber);

            Gson gson = new Gson();
            out.print(gson.toJson(res).trim());
        } else {
            error = msg;
        }
        dbc.close();
    }

    Gson gson = new Gson();
    out.print(gson.toJson(error).trim());

%>
