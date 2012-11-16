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

import org.mirna.designer.interfaces.SequenceCalculatorInterface;

/**
 *
 * @author Attila
 */
public class BaseStackingSequenceCalculator implements SequenceCalculatorInterface {
    
    private int length;
    
    private double primer = 200.0;
    
    private double salt = 0.05;
    
    private double R = 1.987;
    
    private double mg = 0.0;
    
    private double dh;
    
    private double ds;    
    
    public BaseStackingSequenceCalculator() {}
    
    @Override
    public double tmPointCounter( String sequence ) {
        
        this.ds = 0.0;
        this.dh = 0.0;
        
        this.length = sequence.length();
        
        double result = 0.0;
        
        saltcorrections();
        
        terminalcorrections( sequence );
        
        double K = ( this.primer / 2 ) * 1E-9;
        
        for (int i = 0; i < (this.length - 1); i++) {
            
            String nucPair = sequence.substring( i, i+2 );
            
            if( nucPair.equals( "GG" ) || nucPair.equals( "CC" ) ) {
                this.dh += -8;
                this.ds += -19.9;
                
            } else if( nucPair.equals( "AA" ) || nucPair.equals( "TT" ) ) {
                this.dh += -7.9;
                this.ds += -22.2;
                
            } else if( nucPair.equals( "TG" ) || nucPair.equals( "CA" ) ) {
                this.dh += -8.5;
                this.ds += -22.7;
                
            } else if( nucPair.equals( "GT" ) || nucPair.equals( "AC" ) ) {
                this.dh += -8.4;
                this.ds += -22.4;                
                
            } else if( nucPair.equals( "GA" ) || nucPair.equals( "TC" ) ) {
                this.dh += -8.2;
                this.ds += -22.2;                
                
            } else if( nucPair.equals( "AG" ) || nucPair.equals( "CT" ) ) {
                this.dh += -7.8;
                this.ds += -21;                
                
            } else if( nucPair.equals( "GC" ) ) {
                this.dh += -9.8;
                this.ds += -24.4;                
                
            } else if( nucPair.equals( "AT" ) ) {
                this.dh += -7.2;
                this.ds += -20.4;                
                
            } else if( nucPair.equals( "TA" ) ) {
                this.dh += -7.2;
                this.ds += -21.3;                
                
            } else if( nucPair.equals( "CG" ) ) {
                this.dh += -10.6;
                this.ds += -27.2;                
            }            
            
        }
        
        result = ( (1000 * this.dh) / ( ds + ( R * Math.log(K) ) ) ) - 273.15;        
        
        return result;        
        
    }    
    
    private void terminalcorrections( String seq ) {
        
	double deltah=0;
	double deltas=0;
        
	if ( (seq.charAt(0) == 'G') || (seq.charAt(0) == 'C') )
		{
		deltah +=  0.1;
		deltas += -2.8;
		}
        
	if( (seq.charAt(0) == 'A') || (seq.charAt(0) == 'T') )
		{
		deltah+=2.3;
		deltas+=4.1;
		}
		
	if ( (seq.charAt( this.length - 1 ) == 'G' ) || (seq.charAt( this.length - 1 ) == 'C') )
		{
		deltah+=0.1;
		deltas+=-2.8;
		}
        
	if( (seq.charAt( this.length - 1 ) == 'A') || (seq.charAt( this.length - 1 ) == 'T') )
		{
		deltah+=2.3;
		deltas+=4.1;
		}
        
	this.dh += deltah;
	this.ds += deltas;        
        
    }
    
    private void saltcorrections() {
        
        this.salt += ( this.mg / 1E3 * 140 );
        
        double deltas = 0;
        
	deltas += 0.368 * (this.length - 1)* Math.log( salt );
        
	this.ds += deltas;
                
    }

}
