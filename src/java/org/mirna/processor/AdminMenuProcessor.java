/**/

package org.mirna.processor;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mirna.designer.bean.AuthControl;
import org.mirna.designer.bean.Userz;
import org.mirna.designer.controller.AdminDataControl;
import org.mirna.designer.controller.AdminFileDataControl;
import org.mirna.designer.listeners.UserManagerListener;
import org.mirna.designer.logger.ExceptionLogger;

/**
 *
 * @author Attila
 */
public class AdminMenuProcessor extends HttpServlet {
    
    public static final long serialVersionUID = 1L;
    
    private String logdir;
    
    private String usractivlog;
    
    @Override
    public void init() throws ServletException {
        getServletContext().log("getinit init");
        
        logdir = getServletConfig().getInitParameter("logdir") + File.separator + "logs";
        usractivlog = getServletConfig().getInitParameter("usractivlog");
        
    }    
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String action = request.getParameter( "action" );
        
        try{
            
            if( action.equals("modify") ) {
                Collection<Userz> usrList = new AdminDataControl().getUserList( null );
                request.setAttribute( "usrList", usrList );
                
            } else if( action.equals("traffic") ) {
                Collection<AuthControl> traffic = new AdminDataControl().getTraffic();
                request.setAttribute( "traffic", traffic );
                
            } else if( action.equals("online") ) {
                Collection<Userz> online = UserManagerListener.loggedUsersMap.values();
                request.setAttribute( "online", online );
                
            } else if( action.equals("logview") ) {
                AdminFileDataControl adminFDC = new AdminFileDataControl( null, "today" );
                String logText = adminFDC.getDateLog( logdir, usractivlog );
                
                TreeSet<String> dateColl = adminFDC.getDatePoint( logdir );
                Collection<String> userColl = new AdminDataControl().getUsnames();
                  
                request.setAttribute( "logtext", logText );
                request.setAttribute( "datecoll", dateColl );
                request.setAttribute( "usercoll", userColl );
            }
            
            request.setAttribute( "menuaction", action );
            getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );            
            
        } catch( Exception ex) {
            //TODO loggerAdminProcessor
            ExceptionLogger.exceptionServletLog( request, getClass().getName(), ex.toString() );            
            throw new ServletException(ex);
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
