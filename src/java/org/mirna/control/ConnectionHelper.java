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
