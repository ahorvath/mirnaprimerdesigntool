package org.mirna.designer.logger;

import org.apache.log4j.Logger;

/**
 *
 * @author Attila
 */
public class LogInLogOutLogger {
    
    private static Logger logger1 = Logger.getLogger( "useractivity" );
    
    private LogInLogOutLogger() {}
    
    public static void logIn( String usr, String role, String ip ) {
        logger1.info( "usr:" + usr + " action:in [" + role + "] ip:" + ip );
    }
    
    public static void logOut( String usr, String ip ) {
        logger1.info( "usr:" + usr + " action:out ip:" + ip );
    }
    
    public static void logFail( String usr, String ip ) {
        logger1.info( "usr:" + usr + " action:fail ip:" + ip );
    } 
    
    public static void logDestroyed( String usr, String ip ) {
        logger1.info( "usr:" + usr + " action:destroyed ip:" + ip );
    }
    
    public static void auth( String msg0, String msg1, String ip ) {
        logger1.info( "Authentication key request [" + msg0 + "] [" + msg1 + "] ip:" + ip );
    }

    public static void regLogin( String usr, String ip, String timeStamp ) {
        logger1.info( "usr:" + usr + " action:login registration tmStamp:" + timeStamp + " ip:" + ip );
    }
    
    public static void registration( String usr, String msg, String timeStamp ) {
        logger1.info( "usr:" + usr + " action:registration" + " msg:" + msg + " tmStamp:" + timeStamp );
    }

}
