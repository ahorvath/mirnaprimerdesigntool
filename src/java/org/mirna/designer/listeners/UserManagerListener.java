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
