package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import job.UpdateJob;
import job.Job;
import com.google.gson.*;
import dbUtils.*;

public final class update_005fjob_jsp extends org.apache.jasper.runtime.HttpJspBase
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
