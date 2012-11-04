package org.mirna.processor;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mirna.designer.bean.Userz;
import org.mirna.designer.controller.AdminDataControl;
import org.mirna.designer.controller.AdminFileDataControl;
import org.mirna.designer.listeners.UserManagerListener;
import org.mirna.designer.logger.ActivityLogger;
import org.mirna.designer.logger.AdminLogger;
import org.mirna.designer.logger.ExceptionLogger;

/**
 *
 * @author Attila
 */
public class AdminProcessor extends HttpServlet {
    
    public static final long serialVersionUID = 1L;
    
    private static String logdir = null;
    private static String usractivlog = null;
    private static String rootlog = null;
    private static String exceptlog = null;
    
    @Override
    public void init() throws ServletException {
        getServletContext().log("getinit init");
        
        logdir = getServletConfig().getInitParameter("logdir") + File.separator + "logs";
        usractivlog = getServletConfig().getInitParameter("usractivlog");
        rootlog = getServletConfig().getInitParameter("rootlog");
        exceptlog = getServletConfig().getInitParameter("exceptlog");
        
    }
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String action = null;
        
        try{
            
            action = request.getParameter("action");
            
            //TODO loggerAdminProcessor
            ActivityLogger.activityRequest( request, getClass().getName() );
            
            if( action.equals("create") ) {
                
                Userz usr = new AdminDataControl().getUser( request );
                
                AdminDataControl adminDatControl = new AdminDataControl();
                
                boolean created = adminDatControl.accountCreate( usr );
                
                if( !created ){
                    request.setAttribute( "msg", "Exist username! Please change username!" );
                    request.setAttribute( "usr", usr );
                    
                } else {
                    request.setAttribute( "msg", "Account created! [" + usr.getUsname() + " | " + usr.getUsertype() + "]" );
                    request.removeAttribute( "usr" );
                }
                
                request.setAttribute( "usnamevalid", new Boolean(!created) );
                request.setAttribute( "created", new Boolean(created) );
                
                request.setAttribute( "action", action );
                request.setAttribute( "menuaction", action );
                
                //TODO loggerAdminProcessor
                AdminLogger.accountCreate( request, getClass().getName(), usr.getUsname() + " | " + usr.getUsertype() );
                ActivityLogger.activityResponse(request, getClass().getName() );
                
                getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
                
            } else if( action.equals( "modify" ) ) {
                
                AdminDataControl adminDatCont = new AdminDataControl();
                
                Userz usr = adminDatCont.getUser( request );
                String newPass = request.getParameter("userpass");
                if( newPass.length() > 0 )
                    usr.setPassMD5( newPass );
                
                adminDatCont.updateUser( usr );
                
                request.setAttribute( "action", "modify");
                
                //TODO loggerAdminProcessor
                AdminLogger.accountModify( request, action, usr.getUsname() );
                ActivityLogger.activityResponse(request, getClass().getName() );
                
                request.getRequestDispatcher("/menucontrol").forward( request, response );                
                
            } 
            else if( action.equals( "logview" ) ) {
                
                String uview = request.getParameter( "uview" );
                String uname = request.getParameter( "uname" );
                String udate = request.getParameter( "udate" );
                
                AdminFileDataControl adminFDC = new AdminFileDataControl(uname, udate);
                
                /*user view codes
                 *0 : username
                 *1 : date log
                 *2 : username & date log*/
                if( uview.equals("usrlog") ) {
                    TreeMap<String,String> logText = adminFDC.getNameLog( logdir, usractivlog, uname );
                    request.setAttribute( "logtext", logText );
                    
                    request.setAttribute( "logaction", "duo" );
                    
                } else if( uview.equals("datlog") ) {
                    String logText = adminFDC.getDateLog( logdir, usractivlog );
                    request.setAttribute( "logtext", logText );
                    
                    request.setAttribute( "logaction", "uno" );
                     
                } else if( uview.equals("dat&usrlog") ) {
                    String logText = adminFDC.getBothLog(logdir, uname, udate, usractivlog);
                    request.setAttribute( "logtext", logText );
                    
                    request.setAttribute( "logaction", "uno" );
                }
                
                TreeSet<String> dateColl = adminFDC.getDatePoint( logdir );
                Collection<String> userColl = new AdminDataControl().getUsnames();
                
                request.setAttribute( "datecoll", dateColl );
                request.setAttribute( "usercoll", userColl );
                
                request.setAttribute( "menuaction", action );
                
                //TODO loggerAdminProcessor
                AdminLogger.logView(request, getClass().getName(), "[" + uview + "|" + uname + "|" + udate + "]");
                ActivityLogger.activityResponse(request, getClass().getName() );                
                
                request.getRequestDispatcher("index.jsp").forward(request, response);                
                
            }
            else if( action.equals( "shutdown" ) ) {
                String sessid = request.getParameter( "sid" );
                
                String usname = UserManagerListener.getUser(sessid).getUsname();
                
                UserManagerListener.removeUser( sessid );
                
                new AdminDataControl().shutdownUser( usname );
                
                request.setAttribute( "action", "online");
                
                //TODO loggerAdminProcessor
                AdminLogger.shutdown( request, getClass().getName(), usname );
                ActivityLogger.activityResponse(request, getClass().getName() ); 
                
                request.getRequestDispatcher("/menucontrol").forward( request, response );
            }
                    
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
