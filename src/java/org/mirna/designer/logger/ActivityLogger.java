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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.mirna.designer.bean.Userz;
import org.mirna.bean.gene.Gene;
import org.mirna.bean.mirna.MiRNA;

/**
 *
 * @author Attila
 */
public class ActivityLogger {
    
    protected static Logger logger2 = Logger.getLogger( "useractivity" );
    
    public ActivityLogger() {}
    
    private static String unPackMiRNA( Collection<MiRNA> resp ){
        StringBuffer result = new StringBuffer();
        
        for( MiRNA m : resp ) {
            if( result.length() > 0 )
                result.append( "|" );
            result.append( m.getMiRNAName() );
        }
        
        return result.toString();
    }
    
    private static String unPackGene( Collection<Gene> resp ) {
        StringBuffer result = new StringBuffer();
        
        for( Gene g : resp ){
            if( result.length() > 0 )
                result.append( "|" );
            result.append( g.getGeneId() );
        }
        
        return result.toString();
    }
    
    private static String unPackOwn( String own ) {
        StringBuffer result = new StringBuffer();
        
        String[] ownM = own.split("\n");
        
        for( String s : ownM ){
            if( result.length() > 0 )
                result.append( "|" );
            result.append( s );
        }
        
        return result.toString();        
    }
    
    protected static String getLogStyle( HttpServletRequest request, String com, String clazz ) {
        Userz usrz = (Userz)request.getSession().getAttribute("user");
        String usr = usrz.getUsname();
        String rol = usrz.getAdminrol() ? "admin" : "user";
        String action = request.getParameter("action");
        String ip = request.getRemoteAddr();
        
        if( !rol.equals("admin") ) {
            String genedb = (String) request.getSession().getAttribute( "genedb" );
            String miRNAStyle = (String) request.getSession().getAttribute( "miRNAStyle" ); 
            return "usr:" + usr + " action:" + action + " [" + rol + "] [" + com + "] [" + clazz + "] gDb:" + genedb + " mSpec:" + miRNAStyle + " ip:" + ip ;
        } 
        
        return "usr:" + usr + " action:" + action + " [" + rol + "] [" + com + "] [" + clazz + "] ip:" + ip ;
    }
    
    public static void pageView(  HttpServletRequest request, String page ) {
        Userz usr = ((Userz)request.getSession().getAttribute("user"));
        String usname = (usr != null ) ? usr.getUsname() : "unco";
        String usrole = (usr != null ) ? (usr.getAdminrol() ?  "admin" : "user") : "unco";
        String action = request.getParameter("action");
        String ip = request.getRemoteAddr();
        logger2.info( "usr:" + usname + " action:" + action + " [" + usrole + "] [view]" + " page:" + page + " ip:" + ip );
    }
    
    public static void activityFilter( HttpServletRequest request, String clazz ) {
        logger2.info( getLogStyle( request, "filter", clazz) );
    }
    
    public static void activityRequest( HttpServletRequest request, String clazz ) {
        logger2.info( getLogStyle( request, "request", clazz ) );
    }

    public static void activityResponse( HttpServletRequest request, String clazz ) {
        logger2.info( getLogStyle( request, "response", clazz ) );
    }
    
    public static void activityRequestGeneGroup( HttpServletRequest request, String[] geneGroup, String clazz ) {
        logger2.info( getLogStyle( request, "request", clazz) + " geneGroup:" + Arrays.toString(geneGroup) );
    }    
    
    public static void activityRequestWord( HttpServletRequest request, String query, String clazz  ) {
        logger2.info( getLogStyle( request, "request", clazz) + " query:[" + query + "]" );
    }
    
    public static void activityRequestDesign( HttpServletRequest request, List<MiRNA> miRNAs, String clazz) {
        logger2.info( getLogStyle(request, "request", clazz) + " query:(" + miRNAs.size() + ") [" + unPackMiRNA(miRNAs) + "]");
    }
    
    public static void activitiRequestOwnMiRNA( HttpServletRequest request, String own, String clazz ) {
        logger2.info( getLogStyle( request, "request", clazz) + " query:[" + unPackOwn(own) + "]" );
    }    

    public static void activityResponseMiRNA( HttpServletRequest request, Collection<MiRNA> resp, String clazz) {
        logger2.info( getLogStyle( request, "reponse", clazz) + " response:(" + resp.size() + ") 'miRNAName'[" + unPackMiRNA(resp) + "]" );
    }    

    public static void activityResponseGene( HttpServletRequest request, Collection<Gene> resp, String clazz) {
        logger2.info( getLogStyle( request, "reponse", clazz) + " response:(" + resp.size() + ") 'geneID'[" + unPackGene(resp) + "]" );
    }
    
    public static void activityResponseDesignMiRNA( HttpServletRequest request, int oligoCount, String clazz ) {
        logger2.info( getLogStyle( request, "reponse", clazz) + " oligoCount:[" + oligoCount + "]" );
    }
    
}
