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
