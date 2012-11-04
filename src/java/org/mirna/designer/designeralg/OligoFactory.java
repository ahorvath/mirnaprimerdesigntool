package org.mirna.designer.designeralg;

import org.mirna.designer.controller.SequenceCalculator;
import org.mirna.designer.*;
import org.mirna.designer.interfaces.DecimalFormatInterface;
import org.mirna.designer.bean.Oligo;

/**
 *
 * @author Attila
 */
public final class OligoFactory implements DecimalFormatInterface {
    
    private String seqPrefix;
    
    private static String reversePrimer = "GTGCAGGGTCCGAGGT";
    
    public OligoFactory() {}
    
    public Oligo createOligo( String miRNASequence ) {
        
        Oligo result = new Oligo();
        
        /*store mature miRNA sequence*/
        result.setMiRNAMatureSequence( miRNASequence );
        
        /*set mature miRNA sequence*/
        int indexPost = miRNASequence.length() - 6;
        String miRNASequencePre = miRNASequence.substring( 0, indexPost );
        String miRNASequencePost = miRNASequence.substring( indexPost );
        result.setMiRNAMatureSequencePre( miRNASequencePre );
        result.setMiRNAMatureSequencePost( miRNASequencePost );
        
        /*U nucleotide to replace T*/
        miRNASequence = SequenceCalculator.replaceUracilToTimin( miRNASequence );
        
        /*miRNA sequence complementary*/
        String miRNASeqComplenetary = SequenceCalculator.synthesis( miRNASequence );
        
        /*last six nucleotide start index*/
        int lastSix = miRNASeqComplenetary.length() - 6;
        
        /*create sequence prefix*/
        seqPrefix = miRNASequence.substring( 0, lastSix );
        
        /*create and store stem-loop 3' -> 5'*/
        String stemLoop = miRNASeqComplenetary.substring( lastSix );
        result.setStemLoopPrefix( stemLoop );
        
        /*create and store cDNA 3' -> 5'*/
        String cDNA = miRNASeqComplenetary.substring( 0, lastSix );
        result.setCdNA( cDNA );
        
        /*reverse primer ->*/
        
        /*set and store of reverse primer C+G%*/
        result.setReversePrimer( getReversePrimer() );
        double reverseGcPercent = SequenceCalculator.gcPercent( getReversePrimer() );
        result.setReverseGCPercent( decFormat.format( reverseGcPercent  ) );
        
        /*<- reverse primer*/        
        
        return result;        
        
    }

    public String getSeqPrefix() {
        return seqPrefix;
    }

    public static String getReversePrimer() {
        return reversePrimer;
    }

}
