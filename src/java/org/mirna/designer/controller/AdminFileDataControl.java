package org.mirna.designer.controller;

import org.mirna.designer.filters.DatePointFileFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;
import org.mirna.designer.filters.DatePointFileNameFileFilter;
import org.mirna.designer.filters.FileNameFileFilter;
import org.mirna.designer.logger.ExceptionLogger;

/**
 *
 * @author Attila
 */
public class AdminFileDataControl {
    
    private String uname;
    
    private String udate;
    
    public AdminFileDataControl() {}
    
    public AdminFileDataControl( String uname, String udate ) {
        this.uname = uname;
        this.udate = udate;
    }
    
    public TreeSet<String> getDatePoint( String logdir ) throws IOException {
        
        File file = new File( logdir );
        
        if( !file.isDirectory() ){
            return new TreeSet<String>();
        }
        
        DatePointFileFilter datePointFF = new DatePointFileFilter();
        
        file.listFiles( datePointFF    );
        
        return new TreeSet<String>( datePointFF.getExtensions() );
    }
    
    //*************************************************************************
    
    public TreeMap<String,String> getNameLog( String pathLogDir, String logFileName, String usname ) throws IOException {
        TreeMap<String,String> result = new TreeMap<String, String>();
        
        File file = new File( pathLogDir );
        
        if( !file.isDirectory() ) {
            return result;
        }
        
        String cont = "usr:" + usname;
        
        FileNameFileFilter fileNameFF = new FileNameFileFilter( logFileName );
        
        File[] files = file.listFiles( fileNameFF );
        
        BufferedReader buff = null;
        
        String line = null;
        
        for( File f : files ) {
            
            buff = new BufferedReader( new FileReader(f) );
            
            StringBuffer sbuff = new StringBuffer();
            
            line = null;
            
            while( (line=buff.readLine()) != null ) {
                
                if( line.contains( cont ) ) {
                    sbuff.append( line );
                    sbuff.append( "\n" );
                }
                
            }
            
            if( sbuff.length() > 0 ) {
                
                String extension = DesignerUtil.getFileExtension( f.getName() );
                
                if( extension.equals("log") ) {
                    extension = "today";
                }
                
                result.put( extension + "_" + usname, sbuff.toString() );
            }
            
        }
        
        return result;
    }
    
    public String getDateLog( String pathLogDir, String logFileName ) throws IOException {
        
        StringBuffer result = new StringBuffer();
        
        String pathName = pathLogDir + File.separator + logFileName + ".log";
        
        if( !this.udate.equals("today") ) {
            pathName += "." + udate;
        }
        
        File logFile = new File( pathName );
        
        try{
            
            BufferedReader buff = new BufferedReader( new FileReader(logFile) );

            String line = null;

            while( (line = buff.readLine()) != null ){
                result.append( line );
                result.append( "\n" );
            }
            
        } catch(FileNotFoundException fnf) {
            ExceptionLogger.exceptionServiceLog( this.getClass().toString(), fnf.toString() );
            result.append( udate + " log not found!" );
        }
        
        return result.toString();
    }
    
    public String getBothLog( String pathLogDir, String usname, String udate, String logFileName ) throws IOException {
        StringBuffer result = new StringBuffer();
        
        File file = new File( pathLogDir );
        
        if( !file.isDirectory() ) {
            return result.toString();
        }
        
        File[] files = file.listFiles( new DatePointFileNameFileFilter(usname, udate, logFileName) );
        
        String cont = "usr:" + usname;
        
        for( File f : files ) {
            
            BufferedReader buff = new BufferedReader( new FileReader( f ) );
            
            String line = null;
            
            while( (line=buff.readLine()) != null ) {
                
                if( line.contains( cont ) ) {
                    result.append( line );
                    result.append( "\n" );
                }
                
            }
            
        }
        
        return result.toString();
    }
    
    
    
}
