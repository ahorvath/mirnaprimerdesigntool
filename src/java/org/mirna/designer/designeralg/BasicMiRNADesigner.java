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
public class BasicMiRNADesigner extends MiRNADesigner {
    
//    private double reverseTm;
    
//    private int primerCollSize;
    
//    protected SequenceCalculatorInterface sequenceCalculator;
    
    public BasicMiRNADesigner( int primerCollSize, String reversePrimer ) {
        
        super( primerCollSize, reversePrimer );
        
//        this.primerCollSize = primerCollSize;
        
//        sequenceCalculator = createCalculator();
        
//        reverseTm = tmPointNumerator( this.reversePrimer );
        
    }
    
    @Override
    protected SequenceCalculatorInterface createCalculator() {
        
        return new BasicSequenceCalculator();
        
    }
    
//    @Override
//    public TmPoint tmPointFactory( String seq ) {
//        
//        TmPoint result = new TmPoint();
//        
//        result.setSequence( seq );
//        
//        result.setTmPoint( tmPointNumerator( seq ) );
//        
//        result.setGcPercent( SequenceCalculator.gcPercent( seq ) );
//        
//        return result;        
//    }    
//    
//    @Override
//    public double tmPointNumerator( String seq ) {
//        
//        return sequenceCalculator.tmPointCounter( seq );
//        
//    }
//    
//    /*createForwardPrimer method are submetods ->*/
//    public boolean tmPointCut( TmPoint oldTmPoint, TmPoint newTmPoint ) {
//        
//        return (newTmPoint.getTmPoint() > oldTmPoint.getTmPoint()) ? true : false;
//        
//    }
//    
//    public void addTmPoint( TmPoint newTmPoint ) {
//        
//        if( (newTmPoint.getSequence().length() > this.minNucNumber) && (!tmPoints.contains( newTmPoint )) ) {
//            
//            newTmPoint.setTmPointS( this.decFormat.format( newTmPoint.getTmPoint() ) );
//            
//            newTmPoint.setGcPercentS( this.decFormat.format( newTmPoint.getGcPercent() ) );
//            
//            this.tmPoints.add( newTmPoint );
//        }
//        
//    }
//    /*<- createForwardPrimer method are submetods*/
//    
//    /*desing forward primer sequence (deep searcher with cut)->*/
//    @Override
//    public TmPoint forwardPrimerSearcher( TmPoint tmPoint, int depth, int nucleotideNumber, double tempLimit  ) {
//        
//        int l = tmPoint.getSequence().length();
//        
//        if( l == depth )
//            return tmPoint;
//        
//        String newSeqT = 'T' + tmPoint.getSequence();
//        TmPoint newTmPointT = tmPointFactory( newSeqT );
//        newTmPointT = forwardPrimerSearcher( newTmPointT, depth, nucleotideNumber, tempLimit);
//        
//        if( tmPoint.isBetter( newTmPointT, nucleotideNumber, tempLimit) )
//            newTmPointT = tmPoint;
//        
//        String newSeqG = 'G' + tmPoint.getSequence();
//        TmPoint newTmPointG = tmPointFactory( newSeqG );
//        
//        /*tree cut ->*/
//        if( tmPointCut( newTmPointT, newTmPointG) ) {
//            addTmPoint( newTmPointT );//Tm point to buffer
//            return newTmPointT;
//        }
//        /*<- tree cut*/
//        
//        newTmPointG = forwardPrimerSearcher( newTmPointG, depth, nucleotideNumber, tempLimit);
//        
//        if( newTmPointG.isBetter( newTmPointT, nucleotideNumber, tempLimit) )
//            newTmPointT = newTmPointG;
//        
//        addTmPoint( newTmPointT );//Tm point to buffer
//        
//        return newTmPointT;
//        
//    }
//    /*<- desing forward primer sequence (deep searcher)*/    
//    
//    /*set strat tm point ->*/
//    @Override
//    public String createBeginMatureMiRNA( String sequence ) {
//        
//        double tmPoint = tmPointNumerator( sequence );
//        
//        if( tmPoint > this.tempLimit ) {
//            
//            while( tmPoint > this.tempLimit ) {
//
//                int l = sequence.length();
//
//                sequence = sequence.substring( 0, ( l-1 ) );
//
//                tmPoint = tmPointNumerator( sequence );
//
//            }
//            
//        } else {
//            
//            StringBuffer tmBuffer = new StringBuffer( sequence );
//            
//            while( tmPoint < this.tempLimit ) {
//
//                tmBuffer = tmBuffer.insert( 0, "G"); 
//
//                tmPoint = tmPointNumerator( tmBuffer.toString() );
//
//            }
//            
//            sequence = tmBuffer.substring( 1 ).toString();
//        }
//        
//        return sequence;
//        
//    }
//    /*<- set start tm point*/
//    
//    /*forward primer creator is control ->*/
//    @Override
//    public List<TmPoint> design() {
//        
//        TmPoint tmPoint = new TmPoint();
//        
//        this.seqPrefix = createBeginMatureMiRNA( this.seqPrefix );
//        
//        tmPoint.setSequence( this.seqPrefix );
//        tmPoint.setTmPoint( tmPointNumerator( this.seqPrefix ) );
//        tmPoint.setGcPercent( SequenceCalculator.gcPercent( this.seqPrefix ) );
//        
//        this.tmPoints = new Vector<TmPoint>();
//        
//        forwardPrimerSearcher( tmPoint, this.depth, this.minNucNumber, this.reverseTm );
//        
//        Collections.sort( this.tmPoints );
//        
//        int subIndex = this.tmPoints.size();
//        
//        if( subIndex > this.primerCollSize )
//            subIndex = this.primerCollSize;
//        
//        return this.tmPoints.subList( 0, subIndex );
//    }
//    /*<- forward primer creator is control*/
    
}
