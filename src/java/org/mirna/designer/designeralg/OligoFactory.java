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
