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
