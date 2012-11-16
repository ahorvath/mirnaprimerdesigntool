/*
* The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection. The usergets the best result by two different Tm calculating methods.  The tool designs the miRNA specific sequence of the stem-loop RT primer as well. 
*
* copyright (C) 2009-2012 Astrid Research Ltd. 
* copyright (C) November, 2012 University of Debrecen, Clinical Genomic Center, Medical and Health Science Center, Debrecen, Hungary
*
* The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection.  The usergets the best result by two different Tm calculating methods.  The tool designs the miRNA specific sequence of the stem-loop RT primer as well. 
*
*    miRNA Design Tool is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    miRNA Design Tool is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with miRNA Primer Design Tool.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.mirna.designer.filters;

import java.io.*;
import javax.servlet.http.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.mirna.designer.bean.Userz;

/**
 *
 * @author Attila
 */
public class ValidationFilter implements Filter {

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public ValidationFilter() {
    } 

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
	throws IOException, ServletException {
	if (debug) log("AuthenticationFilter:DoBeforeProcessing");
	//
	// Write code here to process the request and/or response before
	// the rest of the filter chain is invoked.
	//

	//
	// For example, a logging filter might log items on the request object,
	// such as the parameters.
	//
	/*
	 for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	     String name = (String)en.nextElement();
	     String values[] = request.getParameterValues(name);
	     int n = values.length;
	     StringBuffer buf = new StringBuffer();
	     buf.append(name);
	     buf.append("=");
	     for(int i=0; i < n; i++) {
	         buf.append(values[i]);
	         if (i < n-1)
	             buf.append(",");
	     }
	     log(buf.toString());
	 }
	*/
	
    } 

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
	throws IOException, ServletException {
	if (debug) log("AuthenticationFilter:DoAfterProcessing");
	//
	// Write code here to process the request and/or response after
	// the rest of the filter chain is invoked.
	//
	
	//
	// For example, a logging filter might log the attributes on the
	// request object after the request has been processed. 
	//
	/*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
	*/
	//

	//
	// For example, a filter might append something to the response.
	//
	/*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
	*/
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
	throws IOException, ServletException {

	    if (debug) log("AuthenticationFilter:doFilter()");

	doBeforeProcessing(request, response);
	
	Throwable problem = null;

	try {
            
            HttpServletRequest req = (HttpServletRequest)request;
            HttpServletResponse res = (HttpServletResponse) response;
            
            Userz usr = (Userz) req.getSession().getAttribute("user");
            boolean valid = (usr != null) ? usr.getValid() : false;
            
            if( (usr != null) && (usr.getSessid().equals( req.getSession().getId() )) && (valid) ){
                chain.doFilter(request, response);
                
            } else {
                if( usr != null )
                    req.getSession().removeAttribute("user");
                
                res.sendRedirect( req.getContextPath() + "/index.jsp" );
            }
	}
	catch(Throwable t) {
	    //
	    // If an exception is thrown somewhere down the filter chain,
	    // we still want to execute our after processing, and then
	    // rethrow the problem after that.
	    //
	    problem = t;
	    t.printStackTrace();
	}

	doAfterProcessing(request, response);

	//
	// If there was a problem, we want to rethrow it if it is
	// a known type, otherwise log it.
	//
	if (problem != null) {
	    if (problem instanceof ServletException) throw (ServletException)problem;
	    if (problem instanceof IOException) throw (IOException)problem;
	    sendProcessingError(problem, response);
	}
    }

    
    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
	return (this.filterConfig);
    }


    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {

	this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter 
     *
     */
    @Override
    public void destroy() { 
    }


    /**
     * Init method for this filter 
     *
     */
    @Override
    public void init(FilterConfig filterConfig) { 

	this.filterConfig = filterConfig;
	if (filterConfig != null) {
	    if (debug) { 
		log("AuthenticationFilter:Initializing filter");
	    }
	}
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {

	if (filterConfig == null) return ("AuthenticationFilter()");
	StringBuffer sb = new StringBuffer("AuthenticationFilter(");
	sb.append(filterConfig);
	sb.append(")");
	return (sb.toString());

    }



    private void sendProcessingError(Throwable t, ServletResponse response) {
	
	String stackTrace = getStackTrace(t); 

	if(stackTrace != null && !stackTrace.equals("")) {

	    try {
		    
		response.setContentType("text/html");
		PrintStream ps = new PrintStream(response.getOutputStream());
		PrintWriter pw = new PrintWriter(ps); 
		pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
		    
		// PENDING! Localize this for next official release
		pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n"); 
		pw.print(stackTrace); 
		pw.print("</pre></body>\n</html>"); //NOI18N
		pw.close();
		ps.close();
		response.getOutputStream().close();
	    }
		
	    catch(Exception ex){ }
	}
	else {
	    try {
		PrintStream ps = new PrintStream(response.getOutputStream());
		t.printStackTrace(ps);
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex){ }
	}
    }

    public static String getStackTrace(Throwable t) {

	String stackTrace = null;
	    
	try {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    t.printStackTrace(pw);
	    pw.close();
	    sw.close();
	    stackTrace = sw.getBuffer().toString();
	}
	catch(Exception ex) {}
	return stackTrace;
    }

    public void log(String msg) {
	filterConfig.getServletContext().log(msg); 
    }

    private static final boolean debug = false;
}