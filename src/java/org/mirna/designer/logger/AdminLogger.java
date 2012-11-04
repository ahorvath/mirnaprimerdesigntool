package org.mirna.designer.logger;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Attila
 */
public class AdminLogger extends ActivityLogger {
    
    protected AdminLogger() {
        super();
    }
    
    public static void accountCreate( HttpServletRequest request, String clazz, String usname ) {
        logger2.info( getLogStyle( request, "response", clazz ) + " username:" + usname );
    }
    
    public static void accountModify( HttpServletRequest request, String clazz, String usname ) {
        logger2.info( getLogStyle( request, "response", clazz ) + " username:" + usname );
    }
    
    public static void logView( HttpServletRequest request, String clazz, String logstatus ) {
        logger2.info( getLogStyle( request, "response", clazz ) + " status:" + logstatus );
    }
    
    public static void shutdown( HttpServletRequest request, String clazz, String usname ) {
        logger2.info( getLogStyle( request, "response", clazz ) + " uername:" + usname );
    }    
}
