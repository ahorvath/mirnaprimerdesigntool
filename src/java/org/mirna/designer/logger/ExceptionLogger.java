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
