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
package org.mirna.control;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.mirna.designer.logger.ExceptionLogger;

/**
 *
 * @author Attila
 */
public class ConnectionHelper {
    
    private static SessionFactory sessionFactory;
    
    public ConnectionHelper() {}
    
    public static Session getSession() {
        
        Session result = null;
        
       try {
           
          if ( sessionFactory == null ) {
              sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
          }
          
          Class.forName("org.postgresql.Driver");
          
          result = sessionFactory.openSession();
          
      } catch (Throwable ex) {
          //TODO loggerConnectionHelper
          ExceptionLogger.exceptionServiceLog( ConnectionHelper.class.getName(), ex.toString() );
          ex.printStackTrace();
      }
       
      return result; 
    }

}
