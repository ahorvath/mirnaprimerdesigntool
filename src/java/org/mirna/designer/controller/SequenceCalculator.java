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
