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
/*
 * miRNA Design Tool - authentication
 */

package org.mirna.designer.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mirna.designer.bean.Userz;
import org.mirna.designer.logger.ExceptionLogger;
import org.mirna.designer.logger.LogInLogOutLogger;
import org.mirna.control.DataControl;
import org.mirna.control.exceptions.CookieEnabledException;
import org.mirna.designer.listeners.UserManagerListener;

/**
 *
 * @author Attila
 */
public class Authentication extends HttpServlet {
    
    public static final long serialVersionUID = 1L;
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            DataControl dataControl = new DataControl();

            /*password data processing ->*/
            String uri = request.getContextPath();
            
            String sessID = request.getSession().getId();

            String passwd_data = request.getParameter("password_data");
            
            String username = request.getParameter("username");

            Userz usr = dataControl.identification(sessID, username, passwd_data);

            /*data of registration ->*/
            String ip = request.getRemoteAddr();
            String host = request.getRemoteHost();
            Timestamp ts = new Timestamp( System.currentTimeMillis() );
            /*<- data of registration*/
            
            if( usr != null ){
                
                /*registration ->*/
                dataControl.regist( ip, host, ts, true );
                
                //TODO loggerAuthentication
                LogInLogOutLogger.regLogin( username, ip, ts.toString() );
                /*<- registration*/                
                
                usr.setPassMD5( "" );
                usr.setSessid( sessID );
                usr.setIp( ip );
                usr.setOndate( ts.toString() );
                request.getSession().setAttribute("user", usr);
                
                //TODO loggerAuthentication
                String rol = usr.getAdminrol() ? "admin" : "user";
                LogInLogOutLogger.logIn( username, rol, ip );
                
                //TODO loggedAddUser
                UserManagerListener.addUser( request.getSession() );
                
                uri += "/index.jsp?action=startpage";
                
            } else {
                
                dataControl.regist( ip, host, ts, false );
                
                //TODO loggerAuthentication
                LogInLogOutLogger.logFail( username, request.getRemoteAddr() );
                
                uri += "/index.jsp?action=invalidpass";
            }
            /*<- password data processing*/
            
            response.sendRedirect( response.encodeURL( uri ) );
            
        } catch( Exception ex ) {
            //TODO loggerAuthentication
            ExceptionLogger.exceptionServletLog( request, getClass().getName(), ex.toString() );
            throw new CookieEnabledException();
        } 
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
