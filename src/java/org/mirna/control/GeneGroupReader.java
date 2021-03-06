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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.mirna.designer.logger.ExceptionLogger;
import org.mirna.bean.gene.GeneGroup;

/**
 *
 * @author Attila
 */
public class GeneGroupReader {
    
    private InputStream iStream;
    
    public GeneGroupReader( InputStream iStream ) {
        
        this.iStream = iStream;
        
    }
    
//    public void readGeneGroups( BufferedReader buff, int level ) {
//        int n = 0;
//        String line = buff.readLine();
//        
//        if( line == null )
//            return;//gene group collection
//        
//        while( line.charAt(n) == '>' )
//            ++n;
//        
//        if( n > level ) {
//            level = n;
//            GeneGroup geneGroup = new GeneGroup( line, level );
//        }
//        
//    }
    
    public void storeGeneGroup( GeneGroup mainGeneGroup, GeneGroup subGeneGroup ) {
        
        if( subGeneGroup != null )
            mainGeneGroup.getGeneGroupColl().add( subGeneGroup );
    }
    
    public ArrayList<GeneGroup> getGeneGroups() {
        
        ArrayList<GeneGroup> result = new ArrayList<GeneGroup>();
        
        InputStreamReader iStreamReader = null;
        
        try {

            iStreamReader = new InputStreamReader( iStream );
            
            BufferedReader buff = new BufferedReader( iStreamReader );
            
            String line = null;
            
            GeneGroup geneGroup1 = null;
            GeneGroup geneGroup2 = null;
            GeneGroup geneGroup3 = null;
            
            int level = 0;
            
            int lg1 = 0;
            int lg2 = 0;
            int lg3 = 0;
            
            String tmp1 = null;
            String tmp2 = null;
            String tmp3 = null;
            
            while( (line = buff.readLine()) != null ) {
                
                level = 0;
                
                while( line.charAt(level) == '>' )
                    ++level;
                
                switch( level ) {
                    
                    case 1: 
                        if ( geneGroup1 != null ) {
                            
                            storeGeneGroup(geneGroup2, geneGroup3);
                            storeGeneGroup(geneGroup1, geneGroup2);
                            result.add( geneGroup1 );
                            
                            geneGroup1 = null;
                            geneGroup2 = null;
                            geneGroup3 = null;
                            
                            lg2 = 0;
                            lg3 = 0;                            
                        }
                        
                        geneGroup1 = new GeneGroup( line.substring( level  ), level  );
                        tmp1 = Integer.toString( lg1++ );
                        geneGroup1.setLevelGroup( tmp1 );
                        break;
                        
                    case 2:
                        storeGeneGroup(geneGroup2, geneGroup3);
                        storeGeneGroup( geneGroup1, geneGroup2 );
                        
                        geneGroup3 = null;
                        
                        geneGroup2 = new GeneGroup( line.substring( level  ), level  );
                        tmp2 = tmp1 + ":" + Integer.toString( lg2++ );
                        geneGroup2.setLevelGroup( tmp2 );
                        break;
                        
                    case 3:
                        storeGeneGroup( geneGroup2, geneGroup3 );
                        geneGroup3 = new GeneGroup( line.substring( level  ), level  );
                        tmp3 = tmp2 + ":" + Integer.toString( lg3++ );
                        geneGroup3.setLevelGroup( tmp3 );
                        break;
                        
                }
                
            }
            
            storeGeneGroup(geneGroup2, geneGroup3);
            storeGeneGroup(geneGroup1, geneGroup2);
            result.add( geneGroup1 );            

        } catch (FileNotFoundException ex) {
            //TODO loggerGeneGroupReader
            ExceptionLogger.exceptionServiceLog( getClass().getName(), ex.toString() );
            
        } catch (IOException ex) {
            //TODO loggerGeneGroupReader
            ExceptionLogger.exceptionServiceLog( getClass().getName(), ex.toString() );
            
        } finally {
            try {
                
                iStreamReader.close();
                
            } catch (IOException ex) {
                //TODO loggerGeneGroupReader
                ExceptionLogger.exceptionServiceLog( getClass().getName(), ex.toString() );
            }
        }
        
        result.trimToSize();
        
        return result;
        
    }

}
