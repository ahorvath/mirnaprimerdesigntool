package org.mirna.designer.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mirna.control.ConnectionHelper;
import org.mirna.control.DataControl;
import org.mirna.designer.bean.AuthControl;
import org.mirna.designer.bean.Userz;

/**
 *
 * @author Attila
 */
public class AdminDataControl implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    public AdminDataControl() {}
    
    public List<AuthControl> getTraffic() throws Exception {
        
        Session session = null;
        
        Transaction tr = null;
        
        ArrayList<AuthControl> traffic = null;
        
        try{
            session = ConnectionHelper.getSession();

            tr = session.beginTransaction();            
            
            String hql = "from AuthControl";
            
            Query query = session.createQuery( hql );
            
            traffic = (ArrayList<AuthControl>) query.list();
            
            tr.commit();
        } catch( Exception ex ) {
            tr.rollback();
            throw new Exception( ex.toString() );
            
        } finally {
            session.close();
        }
        
        traffic.trimToSize();
        
        Collections.sort( traffic );
        
        return traffic;        
        
    }

    public boolean isExistingUser ( String usname ) throws Exception {

        Session session = null;

        Transaction tr = null;

        Object result = null;

        try{
            session = ConnectionHelper.getSession();

            tr = session.beginTransaction();

            String hql = "select usr.usname from Userz as usr where usr.usname like :usname";

            Query query = session.createQuery(hql);
            query.setParameter( "usname", usname );

            result = query.uniqueResult();

            tr.commit();
        } catch( Exception ex ) {
            tr.rollback();
            throw new Exception( ex.toString() );

        } finally {
            session.close();
        }

        return ( result != null );

    }
    
    public List<String> getUsnames() throws Exception {
        
        Session session = null;
        
        Transaction tr = null;
        
        ArrayList<String> usrList = null;
        
        try{
            session = ConnectionHelper.getSession();

            tr = session.beginTransaction();            
            
            String hql = "select usr.usname from Userz as usr";
            
            Query query = session.createQuery(hql);
            
            usrList = (ArrayList<String>) query.list();
            
            tr.commit();
        } catch( Exception ex ) {
            tr.rollback();
            throw new Exception( ex.toString() );
            
        } finally {
            session.close();
        }
        
        usrList.trimToSize();
        
        Collections.sort( usrList );
        
        return usrList;        
    }
    
    public List<Userz> getUserList( String usname ) throws Exception{
        
        Session session = null;
        
        Transaction tr = null;
        
        ArrayList<Userz> usrList = new ArrayList<Userz>();
        
        try{
            session = ConnectionHelper.getSession();

            tr = session.beginTransaction();            
            
            String hql = "from Userz";
            
            if( usname != null )
                hql += " as usr where usr.usname like :usname";
            
            Query query = session.createQuery( hql );
            if( usname != null )
                query.setParameter( "usname", usname );
            usrList = (ArrayList<Userz>) query.list();
            
            tr.commit();
        } catch( Exception ex ) {
            tr.rollback();
            throw new Exception( ex.toString() );
            
        } finally {
            session.close();
        }
        
        usrList.trimToSize();
        
        Collections.sort( usrList );
        
        return usrList;
        
    }

    public Long addUser( Userz usr ) throws Exception {

        Long result = null;

        Session session = null;

        Transaction tr = null;

        try{

            session = ConnectionHelper.getSession();

            tr = session.beginTransaction();

            session.save( usr );

            result = usr.getId();

            tr.commit();
        } catch( Exception ex ) {
            tr.rollback();
            throw new Exception( ex.toString() );

        } finally {
            session.close();
        }

        return result;

    }
    
    public boolean accountCreate( Userz usr ) throws Exception {
        
        Object obj = null;
        
        Session session = null;
        
        Transaction tr = null;
        
        try{
            
            String usname = usr.getUsname();

            boolean exist = isExistingUser( usname );

            if( exist )
                return false;

            session = ConnectionHelper.getSession();

            tr = session.beginTransaction();

//            String hql = "from Userz as usr where usr.usname = :usname";
//
//            Query query = session.createQuery(hql);
//            query.setParameter( "usname", usname );
//
//            if( query.uniqueResult() != null )
//                return false;
            
            /*begin password ->*/
            DataControl dataControl = new DataControl();
            byte[] b = dataControl.MD5( usr.getUsname() );
            String pass = dataControl.convertToHex( b );
            usr.setPassMD5( pass );
            /*<- begin password*/
            
            session.save( usr );
            
            tr.commit();
        } catch( Exception ex ) {
            tr.rollback();
            throw new Exception( ex.toString() );
            
        } finally {
            session.close();
        }   
        
        return (obj == null);
        
    }
    
    public void shutdownUser( String usname ) throws Exception {
        
        Session session = null;
        
        Transaction tr = null;
        
        try{
            
            session = ConnectionHelper.getSession();

            tr = session.beginTransaction();            
            
            String hql = "from Userz as usr where usr.usname like :usname";
            
            Query query = session.createQuery(hql);
            query.setParameter( "usname", usname );
            
            Userz usr = (Userz) query.uniqueResult();
            
            usr.setValid( false );
            
            tr.commit();
        } catch( Exception ex ) {
            tr.rollback();
            throw new Exception( ex.toString() );
            
        } finally {
            session.close();
        }        
        
    }
    
    public Userz getUser( HttpServletRequest request ) {
        Userz result = new Userz();
        
        result.setUsname( request.getParameter( "username" ) );
        result.setFirstname( request.getParameter( "userfname" ) ) ;
        result.setLastname( request.getParameter( "userlname" ) ) ;
        result.setComp_org( request.getParameter( "userorg" ) );
        result.setCountry( request.getParameter( "usercountry" ) ) ;
        result.setTel( request.getParameter( "usertel" ) ) ;
        result.setEmail( request.getParameter( "useremail" ) ) ;
        
        String ut = request.getParameter( "usertype" );
        result.setUsertype( ut ) ;
        
        result.setAdminrol( Boolean.valueOf( ut.equals("administrator")) );
        
        String v = request.getParameter( "uservalid" );
        result.setValid( ( (v != null) && (v.equals("true")) )? Boolean.FALSE : Boolean.TRUE );
        
        return result;
    }
    
    public void updateUser( Userz usr ) throws Exception {
        
        Session session = null;
        
        Transaction tr = null;
        
        try{
            
            session = ConnectionHelper.getSession();

            tr = session.beginTransaction();
            
            String hql = "from Userz as usr where usr.usname like :usname";
            
            Query query = session.createQuery(hql);
            query.setParameter( "usname", usr.getUsname() );
            
            Userz tmpUsr = (Userz) query.uniqueResult();
            
            tmpUsr.setFirstname( usr.getFirstname() );
            tmpUsr.setLastname( usr.getLastname() );
            tmpUsr.setComp_org( usr.getComp_org() );
            tmpUsr.setCountry( usr.getCountry() );
            tmpUsr.setEmail( usr.getEmail() );
            tmpUsr.setTel( usr.getTel() );
            tmpUsr.setUsertype( usr.getUsertype() );
            tmpUsr.setAdminrol( usr.getAdminrol() );
            tmpUsr.setValid( usr.getValid() );
            
            String p = usr.getPassMD5();
            if( p != null ) {
                tmpUsr.setPassMD5( p );
            }
            
            tr.commit();
        } catch( Exception ex ) {
            tr.rollback();
            throw new Exception( ex.toString() );
            
        } finally {
            session.close();
        }         
    }
    
}