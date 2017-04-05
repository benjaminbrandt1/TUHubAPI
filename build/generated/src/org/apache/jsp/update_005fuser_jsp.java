package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import modelUser.*;
import com.google.gson.*;
import dbUtils.*;

public final class update_005fuser_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("application/json; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write(" \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");


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


      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
