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
/*Data controller class*/

package org.mirna.control;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mirna.designer.bean.AuthControl;
import org.mirna.designer.bean.LoginProcess;
import org.mirna.designer.bean.Userz;
import org.mirna.designer.logger.ExceptionLogger;
import org.mirna.bean.gene.Gene;
import org.mirna.bean.mirna.MatureMiRNA;
import org.mirna.bean.mirna.MiRNA;
import org.mirna.bean.mirna.MiRNAInfo;

/**
 *
 * @author Attila
 */
public class DataControl implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    private static String classPrefix = "org.mirna.bean.";    
    
    public DataControl() {}
    
    /* <> */
    public boolean passID( String pass ) throws NoSuchAlgorithmException {
        
/*    MessageDigest md = java.security.MessageDigest.getInstance("MD5");
    byte[] bytes = pass.getBytes();
    md.update( bytes );
    byte[] hash = md.digest();
    
StringBuffer hexString = new StringBuffer();

for (int i = 0; i < hash.length; i++) {
    pass = Integer.toHexString(0xFF & hash[i]);

    if (pass.length() < 2) {
        pass = "0" + pass;
    }

    hexString.append(pass);
}*/
    
        
        
        boolean result = false;
        
        String prefix = "Xj0mT2aQc7";
        String postfix = "uIa8lS4";
        
        Session session = ConnectionHelper.getSession();
        
        Transaction tr = session.beginTransaction();
        
        String sql = "SELECT passw FROM designpassword";
        
        Query sqlQuery = session.createSQLQuery( sql );
        String p = sqlQuery.uniqueResult().toString();        
        
        tr.commit();
        
        session.close();
        
        return ( (prefix + pass + postfix).equals( p ) ? true : false );
    }
    /* <> */
    
    /*test method*/
    public void testQuery() {
        
        Session session = ConnectionHelper.getSession();
        
        Transaction tr = session.beginTransaction();
        
//        Query query = session.createQuery( " from MiRNAInfo as mi where mi.miRNA.intergenic like 'false'" );
//        Collection<MiRNAInfo> miRNAColl = query.list();
        
//        String sql = "SELECT {miRNA.*} FROM MIRNAVIEW miRNA";
//        
//        Query query = session.createSQLQuery( sql )
//                             .addEntity( "miRNA", MiRNA.class );
//        
//        List miRNAList = query.list();
        
//        TreeSet<MiRNA> miRNASet = new TreeSet<MiRNA>();
//        
//        String sql = "SELECT {gene.*} FROM INTRAGENICGENEs gene where gene.biologfunc like '%Apoptosis%'";
//        
//        Query query = session.createSQLQuery( sql )
//                             .addEntity( "gene", Gene.class );
//        
//        Collection<Gene> geneColl = query.list();
//        
//        for( Gene gene : geneColl ) {
//            miRNASet.addAll(session.createFilter( gene.getMiRNAInfoColl(), "select this.miRNA").list());
//        }
        
        tr.commit();
        
        session.close();
        
    }
    
    /*Gene kollekcio tagjaibol kiszedi a MiRNA-kat*/
    public Collection<MiRNA> fromGeneCollToMiRNAs( Collection<Gene> geneColl) {
        
        Set<MiRNA> result = new TreeSet<MiRNA>();
        
        for( Gene gene : geneColl )
            for( Object obj : gene.getMiRNAInfoColl() ) {
                MiRNAInfo miRNAInfo = (MiRNAInfo) obj;
                result.add( miRNAInfo.getMiRNA() );
            }
        
        return result;
        
    }
    
    /*MiRNAInfo and MatureMiRNA collection initialisation*/
    private void miRNAInitialize( Collection<MiRNA> miRNAColl ) {
        
        for( MiRNA miRNA : miRNAColl ) {
            
            Hibernate.initialize( miRNA.getMiRNAInfoColl() );
            Hibernate.initialize( miRNA.getMatureMiRNAColl() );
            
        }
        
    }
    
    /*ha geneIdColl != null, akkor azokat a geneket hagyja meg, amelyek szerepelnek benne.*/
    private void geneInitialize( Collection<MiRNA> miRNAColl, Collection<String> geneIdColl ) {
        
        boolean geneIdC = (geneIdColl != null);
        
        boolean containsGeneId = false;
        
        boolean geneNameMiRNA = false;
        
        for( MiRNA miRNA : miRNAColl ){
            
            Iterator<MiRNAInfo> miInfoIterator = miRNA.getMiRNAInfoColl().iterator();
            
            while( miInfoIterator.hasNext() ){
                
                MiRNAInfo miRNAInfo = miInfoIterator.next();
                                  
                Iterator<Gene> geneIterator = miRNAInfo.getGeneColl().iterator();

                while( geneIterator.hasNext() ) {
                    Gene gene = geneIterator.next();

                    containsGeneId = geneIdC ? geneIdColl.contains( gene.getGeneId() ) : false;

                    geneNameMiRNA = (gene.getGeneFullName()).contains( "microRNA" );
                        
                        if( (geneIdC && !containsGeneId) || geneNameMiRNA )
                            geneIterator.remove();
                    
                    }
            }
        }
            
    }
    
    /*OVERLAPING initialisation*/
    public void overlapInit( Collection<MiRNA> result ) {
        
        for( MiRNA miRNA : result ){
            
            List<MiRNAInfo> miRNAInfoColl = miRNA.getMiRNAInfoColl();
            
            for( MiRNAInfo miRNAInfo : miRNAInfoColl ) {
                
                String miRNAInfoStandard = miRNAInfo.getStandard();
                
                if( miRNAInfoStandard.contains(":") ) {
                
                    Collection<Gene> geneColl = miRNAInfo.getGeneColl();

                    for( Gene gene : geneColl ) {
                        
                        if( miRNAInfoStandard.contains( gene.getGeneId() ) )
                            gene.setGeneId( gene.getGeneId() + "_O" );
                        
                    }
                    
                }
                
            }
            
        }
        
    }
    
    /*Load selected gene of groups, intragenic miRNAs*/
    public Collection<MiRNA> loadGenesToMiRNA( String species, String miRNAStyle, Collection<String> groupColl )
    throws Exception {        
        
        TreeSet<MiRNA> result = new TreeSet<MiRNA>();
        
        StringBuffer querySuffix = new StringBuffer();
        
        /*create gene query suffix ->*/
        Iterator<String> groupIterator = groupColl.iterator();
            
        String group = groupIterator.next();

        querySuffix.append( " where gene.biologfunc like '%" + group + "%'");

        while( groupIterator.hasNext() )
            querySuffix.append( " or gene.biologfunc like '%" + groupIterator.next() + "%'" );
        /*<- create gene query suffix*/

        Session session = null;
        
        try{
            
            Class speciesClass = Class.forName( classPrefix + species.toLowerCase() + "." + species + "Gene" );            
            
            session = ConnectionHelper.getSession();

            Transaction tr = session.beginTransaction();        

            String sql = "SELECT {gene.*} FROM " + species + "_" + miRNAStyle + "genes gene";

            Query query = session.createSQLQuery( sql + querySuffix.toString() )
                                 .addEntity( "gene", speciesClass );

            Collection<Gene> geneColl = query.list();

            Collection<String> geneIdColl = new ArrayList<String>();

            for( Gene gene : geneColl ) {
                result.addAll( session.createFilter( gene.getMiRNAInfoColl(), "select this.miRNA").list() );
                geneIdColl.add( gene.getGeneId() );
            }
            
            miRNAInitialize( result );

            geneInitialize( result, geneIdColl );

            tr.commit();
            
        } catch( Exception ex ) {
            //TODO loggerDataControl
            ExceptionLogger.exceptionServiceLog( getClass().getName(), ex.toString() );            
            throw new Exception( ex.getMessage() );
            
        } finally {
            session.close();
        }
        
        overlapInit(result);
        
        return result;
    }
    
    /*Load full intragenic miRNAs*/
    public Collection<MiRNA> loadMiRNAsToMiRNA( String species, String miRNAStyle)
    throws Exception {
        
        List<MiRNA> result = null;
        
        Session session = null;
        
        try{
            
            Class speciesClass = Class.forName( classPrefix + species.toLowerCase() + "." + species + "MiRNA" );

            session = ConnectionHelper.getSession();

            Transaction tr = session.beginTransaction();

            String sql = "SELECT {miRNA.*} FROM " + species + "_" + miRNAStyle + "mirnas miRNA";

            Query query = session.createSQLQuery( sql )
                                 .addEntity( "miRNA", speciesClass );

            result = query.list();
            
            /*initialize miRNA ->*/
            miRNAInitialize( result );
            /*<- initialize miRNA*/

            /*initialize gene ->*/
            geneInitialize( result, null );
            /*<- initialize gene*/        

            Collections.sort( result );

            tr.commit();
            
        } catch( Exception ex ) {
            throw new Exception( ex.getMessage() );
            
        } finally {
            session.close();
        }
        
        overlapInit( result  );
        
        return result;
        
    }
    
    public ArrayList<MiRNA> loadMiRNAToIDs( String species, String miRNAStyle, String[] IDs  )
    throws Exception {
        
        ArrayList<MiRNA> result = new ArrayList<MiRNA>();

        Session session = null;
        
        try{
            
            Class speciesClass = Class.forName( classPrefix + species.toLowerCase() + "." + species + "MiRNA" );

            session = ConnectionHelper.getSession();

            Transaction tr = session.beginTransaction();
            
            for( String ID : IDs ) {

                String sql = "SELECT {miRNA.*} " +
                             "FROM " + species + "_" + miRNAStyle + "mirnas miRNA " +
                             "WHERE miRNA.id = " + new Long( ID );

                Query query = session.createSQLQuery( sql )
                                     .addEntity( "miRNA", speciesClass );
                
                MiRNA miRNA = (MiRNA) query.uniqueResult();
                
                miRNA.getMatureMiRNAColl().size();

                result.add( miRNA );

                /*initialize miRNA ->*
                miRNAInitialize( result );
                /*<- initialize miRNA*/

                /*initialize gene ->*
                geneInitialize( result, null );
                /*<- initialize gene*/  
            
            }

            Collections.sort( result );

            tr.commit();
            
        } catch( Exception ex ) {
            throw new Exception( ex.getMessage() );
            
        } finally {
            session.close();
        }
        
//        overlapInit( result  );
        
        return result;        
        
    }
    
    public boolean getValidMiRNA( String pattern, String seq, int validDiff ) {
        
        int pLength = pattern.length();
        int sLength = seq.length();
        
        int basicDiff = sLength - pLength;
        
        int diff = 0;
        
        for( int i=0; (i < basicDiff) || (i == 0); i++ ) {
            
            int tmpDiff = 0;
            
            for( int j=0; j < pLength; j++ ) {
                
                if( seq.charAt(j+i) != pattern.charAt(j) ){
                    ++tmpDiff;
                }
                
                if( tmpDiff > validDiff )
                    break;
                
            }
            
            if( (tmpDiff <= validDiff) && ( (tmpDiff < diff) || (diff == 0) ) ) {
                diff = tmpDiff;
            }
            
        }
        
        return (diff != 0);
    }
    
    public TreeMap<String,List<String>> loadMiRNAToCondition( String species, String pattern )
    throws Exception {
        
        TreeMap<String,List<String>> result = new TreeMap<String, List<String>>();
        
        Session session = null;
        
        Transaction tr = null;
        
        try {
            
            int validDiff = 3;
            
            String speciesClass = species + "MatureMiRNA";
            
            session = ConnectionHelper.getSession();
            
            tr = session.beginTransaction();
            
            int patternLength = pattern.length();
            
            String hql = "from " + speciesClass + " as mm " +
                         "where mm.matureSeq not like '" + pattern + "' " + 
                         "and abs(" + patternLength + " - length(mm.matureSeq)) between 0 and "+ validDiff;
            
            Query query = session.createQuery( hql );
            
            TreeSet<MatureMiRNA> mmList = new TreeSet<MatureMiRNA>( query.list() );
            
            int i = -1;
            
            Iterator<MatureMiRNA> iterator = mmList.iterator();
            
            while( iterator.hasNext() ){
                
                MatureMiRNA mm = iterator.next();
                
                ++i;
                
                int seqLength = mm.getMatureSeq().length();
                String seq = mm.getMatureSeq();
                
                boolean validMiRNA = true;

                if( seqLength < patternLength ) {
                    validMiRNA = getValidMiRNA( seq, pattern, validDiff );

                } else {
                    validMiRNA = getValidMiRNA( pattern, seq, validDiff );

                }

                if( !validMiRNA )
                    iterator.remove();
                
            }
            
            tr.commit();
            
        } catch( Exception ex ) {
            tr.rollback();
            throw new Exception( ex.getMessage() );
            
        } finally {
            session.close();
        }
        
        return result;
    }
    
    public Collection<MiRNA> loadMiRNAToName( String species, String miRNAStyle, String miRNAName )
    throws Exception {
        
        List<MiRNA> result = null;
        
        Session session = null;
        
        try{
            
            Class speciesClass = Class.forName( classPrefix + species.toLowerCase() + "." + species + "MiRNA" );        

            session = ConnectionHelper.getSession();

            Transaction tr = session.beginTransaction();

            String sql = "SELECT {miRNA.*} " +
                         "FROM " + species + "_" + miRNAStyle + "mirnas miRNA " +
                         "WHERE miRNA.miRNAName like '%" + miRNAName + "%'";

            Query query = session.createSQLQuery( sql )
                                 .addEntity( "miRNA", speciesClass );

            result = new ArrayList<MiRNA>( query.list() );
            
            /*initialize miRNA ->*/
            miRNAInitialize( result );
            /*<- initialize miRNA*/

            /*initialize gene ->*/
            geneInitialize( result, null );
            /*<- initialize gene*/

            Collections.sort( result );

            tr.commit();
        
        } catch( Exception ex ) {
            throw new Exception( ex.getMessage() );
            
        } finally {
            session.close();
        }
        
        overlapInit(result);
        
        return result;
        
    }
    
    public Collection<Gene> loadGeneToName( String species, String miRNAStyle, String geneProperties )
    throws Exception {
        
        Session session = null;
        
        TreeSet<Gene> geneSet = new TreeSet<Gene>();
        
        StringTokenizer genePropTokenizer = new StringTokenizer( geneProperties, "\r\n" );
        
        try{
            
            while( genePropTokenizer.hasMoreTokens() ) {

                String geneProp = genePropTokenizer.nextToken().trim();

                /*GeneID tokenizer ->*/
                if( geneProp.contains(":") ) {

                    StringTokenizer geneIdTokenizer = new StringTokenizer( geneProp, ":" );
                    geneIdTokenizer.nextToken();                
                    geneProp = geneIdTokenizer.nextToken().trim();

                }
                /*<- GeneID tokenizer*/

                session = ConnectionHelper.getSession();

                Transaction tr = session.beginTransaction();

                Class speciesClass = Class.forName( classPrefix + species.toLowerCase() + "." + species + "Gene" );                    

                //setting search properties
                String sqlID = " gene.geneId like '%" + geneProp + "%'";
                String sqlName = " UPPER(gene.geneFullName) like UPPER('%" + geneProp + "%')";
                String sqlSymbol = " UPPER(gene.geneSymbol) like UPPER('%" + geneProp + "%')";
                String sqlAlsoSymbol = " UPPER(gene.geneAlternativeSymbol) like UPPER('%" + geneProp + "%')";
                String sqlTranscriptID = " UPPER(gene.transcriptID) like UPPER('%" + geneProp + "%')";
                String sqlProteinID = " UPPER(gene.proteinID) like UPPER('%" + geneProp + "%')";
                String sqlAffymetrixID = " UPPER(gene.affymetrixID) like UPPER('%" + geneProp + "%')";

                String sql = "SELECT {gene.*} FROM " + species + "_" + miRNAStyle + "genes gene WHERE" + 
                              sqlID + " or " +
                              sqlName + " or " +
                              sqlSymbol + " or " + 
                              sqlAlsoSymbol + " or " +
                              sqlTranscriptID + " or " + 
                              sqlProteinID + " or " + 
                              sqlAffymetrixID;

                Query query = session.createSQLQuery( sql )
                                     .addEntity( "gene", speciesClass );

                List<Gene> geneColl = query.list();
                
                Collection<MiRNA> miRNAColl = new ArrayList<MiRNA>();
                
                for( Gene gene: geneColl )
                    if( geneSet.add(gene) ) {
                        
                        for( Object obj : gene.getMiRNAInfoColl() ) {
                            
                            MiRNAInfo miRNAInfo = (MiRNAInfo) obj;
                            
                            miRNAColl.add( miRNAInfo.getMiRNA() );
                            
                        }
                        
                    }
                
                /*initialize miRNA ->*/
                miRNAInitialize( miRNAColl );
                /*<- initialize miRNA*/
                
                tr.commit();

            }
        
        } catch( Exception ex ) {
            throw new Exception( ex.getMessage() );
            
        } finally {
            session.close();
        }
        
        return geneSet;
        
    }
    
    public String convertToHex( byte[] data ) {
        StringBuffer buf = new StringBuffer();
        
        for (int i = 0; i < data.length; i++) {
            int halfbyte = ( data[i] >>> 4 ) & 0x0F;
            
            int two_halfs = 0;
            
            do {
                    if ((0 <= halfbyte) && (halfbyte <= 9))
                        buf.append((char) ('0' + halfbyte));
                    
                    else
                        buf.append((char) ('a' + (halfbyte - 10)));
                    
                    halfbyte = data[i] & 0x0F;
                    
            } while( two_halfs++ < 1 );
        }
        
        return buf.toString();
    }

    public byte[] MD5( String text ) 
    throws NoSuchAlgorithmException, UnsupportedEncodingException  {
        
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
            
            return md.digest();
    }
    
    public Userz identification( String sessID, String username, String passwd_data )
    throws Exception {
        
        Userz result = null;
        
        Session session = null;
        
        Transaction tr = null;
        
        try{
            
            session = ConnectionHelper.getSession();
            
            tr = session.beginTransaction();
            
            /*new user ->*
            Userz u = new Userz();
            u.setUsname("tesztverzio");
            u.setPassMD5("f2cad0f2c80843466f3f918acbe1c924");
            u.setRole("user");
            u.setValid( Boolean.TRUE );
            
            persistData(u);
            
            Userz u2 = new Userz();
            u2.setUsname("roche");
            u2.setPassMD5("91ff1a483eeadf690487d6a614ed2519");
            u2.setRole("user");
            u2.setValid( Boolean.TRUE );
            
            persistData(u2);
            
            Userz u3 = new Userz();
            u3.setUsname("jbinder");
            u3.setPassMD5("4c8aa8dd24c928f8fe27028b96edcf1c");
            u3.setRole("user");
            u3.setValid( Boolean.FALSE );
            
            persistData(u3);
            
            Userz u4 = new Userz();
            u4.setUsname("Alfred");
            u4.setPassMD5("86703fde9e87dd5c0f8e1072545d0128");
            u4.setAdminrol(Boolean.TRUE);
            u4.setValid(Boolean.TRUE);
            
            persistData(u4);            
            /*<- new user*/
            
            /*get Userz ->*/
            String hql = "from Userz as usr where usr.usname like :usname";
            
            Query hqlQuery = session.createQuery( hql );
            hqlQuery.setParameter( "usname", username );
            Userz user = (Userz) hqlQuery.uniqueResult();
            /*<- get Userz*/
            
            /*login process ->*/
            String HQL = "from LoginProcess as lgp where lgp.sessionID = :sessID";
            
            Query query = session.createQuery(HQL);
            query.setParameter("sessID", sessID);
            
            List<LoginProcess> lgpl = new ArrayList<LoginProcess>( query.list() );
            
            Collections.sort( lgpl );
            
            LoginProcess lgp = lgpl.get(0);
            
            String prepasskey = lgp.getMsg0();
            String postpasskey = lgp.getMsg1();
            
            for( LoginProcess lg : lgpl ){
                session.delete( lg );
            }
            
            tr.commit();
            /*<- login process*/
            
            /*hashing (MD5)->*/
            if( (user != null) && (user.getValid().booleanValue()) ) {
                
                String text = prepasskey + user.getPassMD5() + postpasskey;

                byte[] text_hash = MD5( text );

                String text_data = convertToHex( text_hash );

                if( text_data.equals( passwd_data ) ){
                    result = user;
                }
                
            }
            /*<- hashing (MD5)*/
            
        } catch( Exception ex ) {
            tr.rollback();
            throw new Exception( ex.toString() );
            
        } finally {
            session.close();
        }
        
        return result;
    }
    
    public void regist( String ip, String hozt, Timestamp ts, boolean goal )
    throws Exception{
        
        Session session = null;
        
        Transaction tr = null;
        
        try{

            session = ConnectionHelper.getSession();

            tr = session.beginTransaction();

            String HQL = "from AuthControl as auth where auth.ip = :ip";
            
            Query query = session.createQuery(HQL);
            query.setParameter( "ip", ip );
            
            AuthControl authControl = (AuthControl) query.uniqueResult();
            
            if( authControl == null ){
                AuthControl newAuth = new AuthControl();
                newAuth.setIp( ip );
                newAuth.setHozt( hozt );
                newAuth.setLazt( ts );
                
                if( goal ) {
                    newAuth.setGoal( newAuth.getGoal() + 1 );
                    
                } else {
                    newAuth.setFaild( newAuth.getFaild() + 1 );
                }
                
                session.persist( newAuth );
                
            } else {
                if( goal ){
                    authControl.setGoal( authControl.getGoal() + 1 );
                    authControl.setLazt( ts );
                    
                } else {
                    authControl.setFaild( authControl.getFaild() + 1 );
                    authControl.setLazt( ts );
                }
            }

            tr.commit();
            
        } catch( Exception ex ) {
            tr.rollback();
            throw new Exception( ex.toString() );
            
        } finally {
            session.close();           
        }        
        
    }
    
    /*Object write in the database*/
    public void persistData( Object object )
    throws Exception {
        
        Session session = null;
        
        Transaction tr = null;
        
        try{

            session = ConnectionHelper.getSession();

            tr = session.beginTransaction();

            session.save( object );

            tr.commit();
            
        } catch( Exception ex ) {
            tr.rollback();
            throw new Exception( ex.toString() );
            
        } finally {
            session.close();           
        }
        
    }
    
    /*Object collection in the database*/
    public void persistDataCollection( Collection<Object> objectColl )
    throws Exception {
        
        Session session = null;
        
        Transaction tr = null;
        
        try{
            
            session = ConnectionHelper.getSession();

            tr = session.beginTransaction();

            for( Object object : objectColl )
                session.persist( object );

            tr.commit();
            
        } catch( Exception ex ) {
            tr.rollback();
            throw new Exception( ex.toString() );
            
        } finally {
            session.close();
        }                
        
    }

}