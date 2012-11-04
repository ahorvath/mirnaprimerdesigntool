package org.mirna.designer.designeralg;

import org.mirna.designer.controller.SequenceCalculator;
import org.mirna.designer.*;
import java.util.HashMap;

/**
 *
 * @author Attila
 */
public class BasicSequenceCalculator extends SequenceCalculator {
    
    public BasicSequenceCalculator() {}
    
    /*Tm point counter ->*/
    
    @Override
    public double tmPointCounter( String sequence ) {
        
        return (sequence.length() > 13) ? greateNucleotide(sequence): lessNucleotide(sequence);
        
    }
    
    /*For sequences less than 14 nucleotides the formula is: 
     Tm= ( wA + xT ) * 2 + ( yG + zC ) * 4 */
    private double lessNucleotide( String sequence ) {
        double result = 0.0d;
        
        HashMap<String,Double> citozinGuanin = citozinGuaninNumber( sequence );
        
        //tag1
        double tag1 = 2.0d * citozinGuanin.get( "at" );
        
        //tag2
        double tag2 = 4.0d * citozinGuanin.get( "gc" );
        
        result =  tag1 + tag2;
        
        return result;
    }
    
    /*For sequences longer than 13 nucleotides, the equation used is 
     Tm= 64.9 + 41 * ( yG + zC - 16.4 ) / ( wA + xT + yG + zC ) 
     */
    private double greateNucleotide( String sequence ) {
        double result = 0;
        
        HashMap<String,Double> citozinGuanin = citozinGuaninNumber( sequence );
        
        //tag1
        double tag1 = 64.9d;
        
        //tag2
        double tag2 = 41.0 * ( citozinGuanin.get( "gc" ) - 16.4 );
        
        //tag3
        double tag3 = citozinGuanin.get( "at" ) + citozinGuanin.get( "gc" );
        
        result = tag1 + ( tag2 / tag3 );        
        
        return result;
    }
    
    /*<- Tm point counter*/  

}
