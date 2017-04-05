package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import job.JobSearch;
import job.JobList;
import com.google.gson.*;
import dbUtils.*;

public final class search_005factive_005fjob_005ftitles_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write(" \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

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
        JobList jobList = JobSearch.searchActiveJobTitles(dbc, title, start, numRows);
        out.print(gson.toJson(jobList).trim());
    } else {
        out.print(gson.toJson(msg).trim());
    }
    dbc.close();



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
