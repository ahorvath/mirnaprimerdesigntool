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

import java.util.HashMap;
import org.mirna.designer.interfaces.SequenceCalculatorInterface;

/**
 *
 * @author Attila
 */
public abstract class SequenceCalculator implements SequenceCalculatorInterface {
    
    public SequenceCalculator() {}
    
    /*percent of citozin and guanin ->*/
    public static double gcPercent( String sequence ) {
        
        double l = sequence.length();
        
        HashMap<String,Double> citozinGuanin = citozinGuaninNumber( sequence );
        
        return ( citozinGuanin.get( "gc" )  / l ) * 100.0;
    }
    /*<- percent of citozin and guanin*/
    
    /*number of citozin and guanin ->*/
    public static HashMap<String,Double> citozinGuaninNumber( String sequence ){
        HashMap<String,Double> result = new HashMap<String,Double>( 2 );
        
        double at = 0.0d;
        double gc = 0.0d;
        
        int l = sequence.length();
        
        for( int i=0; i<l; i++ ){
            
            char c = sequence.charAt( i );
            
            switch( c ) {
                case 'A':
                case 'T': ++at; break;
                case 'C':
                case 'G': ++gc; break;
            }
            
        }        
        
        result.put( "at", new Double( at ) );
        result.put( "gc", new Double( gc ) );
        
        return result;
    }
    /*<- number of citozin and guanin*/
    
    /*mature sequence synthesis ->*/
    public static String synthesis(String sequence) {
        
        char[] cArray = sequence.trim().toUpperCase().toCharArray();
        
        StringBuffer result = new StringBuffer( cArray.length );
        
        for( char c : cArray ) {
            
            switch( c ) {
                case 'A': result.append( 'T' ); break;
                case 'T': result.append( 'A' ); break;
                case 'C': result.append( 'G' ); break;
                case 'G': result.append( 'C' ); break;
            }
        }
            
        return result.toString();
        
    }
    /*<- mature sequence synthesis*/

    /*Timin to replace Uracil ->*/
    public static String replaceUracilToTimin(String sequence) {
        
        return sequence.replaceAll( "U", "T");
        
    }    
    /*<- Timin to replace Uracil*/

}
