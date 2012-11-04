package org.mirna.designer.listeners;

import java.util.TreeMap;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.mirna.designer.bean.Userz;
import org.mirna.designer.logger.LogInLogOutLogger;

/**
 *
 * @author Attila
 */
public class UserManagerListener implements HttpSessionListener {
    
    public static TreeMap<String,Userz> loggedUsersMap = new TreeMap<String,Userz>();
    
    public UserManagerListener() {}
    
    /*user manager ->*/
    public static void addUser( HttpSession session ) {
        String sessid = session.getId();
        Userz usr = (Userz) session.getAttribute( "user" );
        loggedUsersMap.put( sessid, usr );
    }
    
    public static Userz getUser( String sessid ) {
        return loggedUsersMap.get( sessid );
    }
    
    public static Userz removeUser( String sessid ) {
        return loggedUsersMap.remove( sessid );
    }
    /*<- user manager*/

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String sessid = se.getSession().getId();
        
        //TODO loggedRemoveUser
        
        if( loggedUsersMap.containsKey( sessid ) ) {
            Userz usr = loggedUsersMap.get( sessid );
            String ip = usr.getIp();
            String usname = usr.getUsname();
            loggedUsersMap.remove( sessid );
            LogInLogOutLogger.logDestroyed( usname, ip );

        } else {
            LogInLogOutLogger.logDestroyed( sessid, " before removed " );

        }

    }

}
