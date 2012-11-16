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
