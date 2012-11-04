package org.mirna.designer.logger;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.mirna.designer.bean.Userz;

/**
 *
 * @author Attila
 */
public class ExceptionLogger {
    
    private static Logger logger3 = Logger.getLogger( "ecxeptionlog" );
    
    private ExceptionLogger() {}
    
    private static String getLogStyle( HttpServletRequest request, String com, String clazz, String ex ) {
        Userz usrz = (Userz)request.getSession().getAttribute("user");
        String usr = (usrz != null) ? usrz.getUsname() : "unco";
        String role = (usrz != null) ? (usrz.getAdminrol() ? "admin" : "user") : "unco";
        
        String ip = request.getRemoteAddr();
        String action = request.getParameter("action");
        
        return "usr:" + usr + " action:" + action + " [" + role + "] [" + com + "] [" + clazz + "]" + " ip:" + ip + "exception:[" + ex + "]";
    }    
    
    public static void exceptionServletLog( HttpServletRequest request, String clazz, String ex ) {
        logger3.info( getLogStyle(request, "request", clazz, ex) );
    }
    
    public static void exceptionServiceLog( String clazz, String ex ) {
        logger3.info( "class:" + clazz + " exception:[" + ex + "]");
    }

}
