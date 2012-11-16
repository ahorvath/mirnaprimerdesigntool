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
