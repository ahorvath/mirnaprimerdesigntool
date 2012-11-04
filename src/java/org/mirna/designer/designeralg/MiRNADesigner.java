package org.mirna.designer.designeralg;

import org.mirna.designer.controller.SequenceCalculator;
import org.mirna.designer.*;
import java.util.ArrayList;
import org.mirna.designer.interfaces.DecimalFormatInterface;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import org.mirna.designer.bean.TmPoint;
import org.mirna.designer.interfaces.MiRNADesignerInterface;
import org.mirna.designer.interfaces.SequenceCalculatorInterface;

/**
 *
 * @author Attila
 */
public class MiRNADesigner implements MiRNADesignerInterface, DecimalFormatInterface {
    
    protected int minNucNumber = 17;    
    protected int depth = 30;
    protected int tempLimit = 48;
//    protected int lastSix = 0;
    
//    protected String seqPrefix;
    protected String reversePrimer;
    private double reverseTm;
    
    protected Vector<TmPoint> tmPoints;
//    protected DecimalFormat decFormat = new DecimalFormat( "#.##" );
    
    private int primerCollSize;
    
    protected SequenceCalculatorInterface sequenceCalculator;
    
    public MiRNADesigner( int primerCollSize, String reversePrimer ) {
        
        this.primerCollSize = primerCollSize;
        
        this.reversePrimer = reversePrimer;
        
        this.sequenceCalculator = createCalculator();
        
        this.reverseTm = tmPointNumerator( this.reversePrimer );
        
    }
    
//    @Override
//    public Oligo oligoInit( String miRNASequence ) {
//        
//        Oligo result = new Oligo();
//        
//        /*store mature miRNA sequence*/
//        result.setMiRNAMatureSequence( miRNASequence );
//        
//        /*set mature miRNA sequence*/
//        int indexPost = miRNASequence.length() - 6;
//        String miRNASequencePre = miRNASequence.substring( 0, indexPost );
//        String miRNASequencePost = miRNASequence.substring( indexPost );
//        result.setMiRNAMatureSequencePre( miRNASequencePre );
//        result.setMiRNAMatureSequencePost( miRNASequencePost );
//        
//        /*U nucleotide to replace T*/
//        miRNASequence = SequenceCalculator.replaceUracilToTimin( miRNASequence );
//        
//        /*miRNA sequence complementary*/
//        String miRNASeqComplenetary = SequenceCalculator.synthesis( miRNASequence );
//        
//        /*last six nucleotide start index*/
//        this.lastSix = miRNASeqComplenetary.length() - 6;
//        
//        /*create sequence prefix*/
//        this.seqPrefix = miRNASequence.substring( 0, lastSix );
//        
//        /*create and store stem-loop 3' -> 5'*/
//        String stemLoop = miRNASeqComplenetary.substring( lastSix );
//        result.setStemLoopPrefix( stemLoop );
//        
//        /*create and store cDNA 3' -> 5'*/
//        String cDNA = miRNASeqComplenetary.substring( 0, lastSix );
//        result.setCdNA( cDNA );
//        
//        /*reverse primer ->*/
//        
//        /*set and store of reverse primer C+G%*/
//        result.setReversePrimer( reversePrimer );
//        double reverseGcPercent = SequenceCalculator.gcPercent( reversePrimer );
//        result.setReverseGCPercent( decFormat.format( reverseGcPercent  ) );
//        
//        /*<- reverse primer*/        
//        
//        return result;
//        
//    }
    
    @Override
    public TmPoint tmPointFactory( String seq ) {
        
        TmPoint result = new TmPoint();
        
        result.setSequence( seq );
        
        result.setTmPoint( tmPointNumerator( seq ) );
        
        result.setGcPercent( SequenceCalculator.gcPercent( seq ) );
        
        return result;        
    }    
    
    @Override
    public double tmPointNumerator( String seq ) {
        
        return sequenceCalculator.tmPointCounter( seq );
        
    }
    
    /*createForwardPrimer method are submetods ->*/
    public boolean tmPointCut( TmPoint oldTmPoint, TmPoint newTmPoint ) {
        
        return (newTmPoint.getTmPoint() > oldTmPoint.getTmPoint()) ? true : false;
        
    }
    
    public void addTmPoint( TmPoint newTmPoint ) {
        
        if( (newTmPoint.getSequence().length() > this.minNucNumber) && (!tmPoints.contains( newTmPoint )) ) {
            
            newTmPoint.setTmPointS( decFormat.format( newTmPoint.getTmPoint() ) );
            
            newTmPoint.setGcPercentS( decFormat.format( newTmPoint.getGcPercent() ) );
            
            this.tmPoints.add( newTmPoint );
        }
        
    }
    /*<- createForwardPrimer method are submetods*/
    
    /*desing forward primer sequence (deep searcher with cut)->*/
    @Override
    public TmPoint forwardPrimerSearcher( TmPoint tmPoint, int depth, int nucleotideNumber, double tempLimit  ) {
        
        int l = tmPoint.getSequence().length();
        
        if( l == depth )
            return tmPoint;
        
        String newSeqT = 'T' + tmPoint.getSequence();
        TmPoint newTmPointT = tmPointFactory( newSeqT );
        newTmPointT = forwardPrimerSearcher( newTmPointT, depth, nucleotideNumber, tempLimit);
        
        if( tmPoint.isBetter( newTmPointT, nucleotideNumber, tempLimit) )
            newTmPointT = tmPoint;
        
        String newSeqG = 'G' + tmPoint.getSequence();
        TmPoint newTmPointG = tmPointFactory( newSeqG );
        
        /*tree cut ->*/
        if( tmPointCut( newTmPointT, newTmPointG) ) {
            newTmPointT.setDeviation( Math.abs( tempLimit - newTmPointT.getTmPoint() ) );//setting deviation
            addTmPoint( newTmPointT );//Tm point to buffer
            return newTmPointT;
        }
        /*<- tree cut*/
        
        newTmPointG = forwardPrimerSearcher( newTmPointG, depth, nucleotideNumber, tempLimit);
        
        if( newTmPointG.isBetter( newTmPointT, nucleotideNumber, tempLimit) )
            newTmPointT = newTmPointG;
        
        newTmPointT.setDeviation( Math.abs( tempLimit - newTmPointT.getTmPoint() ) );//setting deviation
        addTmPoint( newTmPointT );//Tm point to buffer
        
        return newTmPointT;
        
    }
    /*<- desing forward primer sequence (deep searcher)*/    
    
    /*set strat tm point ->*/
    @Override
    public String createBeginMatureMiRNA( String sequence ) {
        
        double tmPoint = tmPointNumerator( sequence );
        
        if( tmPoint > this.tempLimit ) {
            
            while( tmPoint > this.tempLimit ) {

                int l = sequence.length();

                sequence = sequence.substring( 0, ( l-1 ) );

                tmPoint = tmPointNumerator( sequence );

            }
            
        } else {
            
            StringBuffer tmBuffer = new StringBuffer( sequence );
            
            while( tmPoint < this.tempLimit ) {

                tmBuffer = tmBuffer.insert( 0, "G"); 

                tmPoint = tmPointNumerator( tmBuffer.toString() );

            }
            
            sequence = tmBuffer.substring( 1 ).toString();
        }
        
        return sequence;
        
    }
    /*<- set start tm point*/
    
    /*forward primer creator is control ->*/
    @Override
    public List<TmPoint> design( String seqPrefix ) {
        
        TmPoint tmPoint = new TmPoint();
        
        seqPrefix = createBeginMatureMiRNA( seqPrefix );
        
        tmPoint.setSequence( seqPrefix );
        tmPoint.setTmPoint( tmPointNumerator( seqPrefix ) );
        tmPoint.setGcPercent( SequenceCalculator.gcPercent( seqPrefix ) );
        
        this.tmPoints = new Vector<TmPoint>();
        
        forwardPrimerSearcher( tmPoint, this.depth, this.minNucNumber, this.reverseTm );
        
        Collections.sort( this.tmPoints );
        
        int subIndex = this.tmPoints.size();
        
        if( subIndex > this.primerCollSize )
            subIndex = this.primerCollSize;
        
        return new ArrayList<TmPoint>( this.tmPoints.subList( 0, subIndex ) );
    }

    protected SequenceCalculatorInterface createCalculator() {
        return null;
    }

    public String getReverseTm() {
        return decFormat.format( reverseTm );
    }

    /*<- forward primer creator is control*/    

}
