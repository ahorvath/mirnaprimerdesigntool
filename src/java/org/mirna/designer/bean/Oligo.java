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
package org.mirna.designer.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.mirna.designer.designeralg.OligoTmPointCalculation;

/**
 *
 * @author Attila
 */
public class Oligo implements Serializable,Comparable<Oligo> {
    
    public static final long serialVersionUID = 4555878785l;
    
    private String miRNAName;
    private String miRNAVariantName;
    private String miRNALink;
    
    private String miRNAMatureSequence;
    private String miRNAMatureSequencePre;
    private String miRNAMatureSequencePost;
    
    private String stemLoopPrefix;
    private String stemLoopPostfix = "GTTGGCTCTGGTGCAGGGTCCGAGGTATTCGCACCAGAGCCAAC";
                                      
    private String cdNA;
    
//    private String forwardPrimer;
//    private String forwardTm;
//    private String forwardGCPercent;
    
//    private Collection<TmPoint> basicForwardPrimers;
    private List<OligoTmPointCalculation> oligoTmPointCalColl = new ArrayList<OligoTmPointCalculation>();
    
    private String reversePrimer;
    private String reverseTm;
    private String reverseGCPercent;
    
    public Oligo() {} 

    public String getMiRNAName() {
        return miRNAName;
    }

    public void setMiRNAName(String miRNAName) {
        this.miRNAName = miRNAName;
    }
    
    public String getMiRNAVariantName() {
        return miRNAVariantName;
    }

    public void setMiRNAVariantName(String miRNAVariantName) {
        this.miRNAVariantName = miRNAVariantName;
    }    
    
    public String getMiRNALink() {
        return miRNALink;
    }

    public void setMiRNALink(String miRNALink) {
        this.miRNALink = miRNALink;
    }   
    
    public String getMiRNAMatureSequence() {
        return miRNAMatureSequence;
    }

    public void setMiRNAMatureSequence(String miRNAMatureSequence) {
        this.miRNAMatureSequence = miRNAMatureSequence;
    }    
    
    public String getMiRNAMatureSequencePre() {
        return miRNAMatureSequencePre;
    }

    public void setMiRNAMatureSequencePre(String miRNAMatureSequencePre) {
        this.miRNAMatureSequencePre = miRNAMatureSequencePre;
    }

    public String getMiRNAMatureSequencePost() {
        return miRNAMatureSequencePost;
    }

    public void setMiRNAMatureSequencePost(String miRNAMatureSequencePost) {
        this.miRNAMatureSequencePost = miRNAMatureSequencePost;
    }    
    
    public String getStemLoopPrefix() {
        return stemLoopPrefix;
    }

    public void setStemLoopPrefix(String stemLoopPrefix) {
        this.stemLoopPrefix = stemLoopPrefix;
    }
    
    public String getStemLoopPrefixReverse() {
        return new StringBuffer( this.stemLoopPrefix ).reverse().toString();
    }

    public String getStemLoopPostfix() {
        return stemLoopPostfix;
    }

    public void setStemLoopPostfix(String stemLoopPostfix) {
        this.stemLoopPostfix = stemLoopPostfix;
    }

    public String getCdNA() {
        return cdNA;
    }

    public void setCdNA(String cdna) {
        this.cdNA = cdna;
    }
    
    public String getCdNAReverse() {
        return new StringBuffer( this.cdNA ).reverse().toString();
    }

//    public String getForwardPrimer() {
//        return forwardPrimer;
//    }
//
//    public void setForwardPrimer(String forwardPrimer) {
//        this.forwardPrimer = forwardPrimer;
//    }
//    
//    public String getForwardTm() {
//        return forwardTm;
//    }
//
//    public void setForwardTm(String forwardTm) {
//        this.forwardTm = forwardTm;
//    } 
//    
//    public String getForwardGCPercent() {
//        return forwardGCPercent;
//    }
//
//    public void setForwardGCPercent(String forwardGCPercent) {
//        this.forwardGCPercent = forwardGCPercent;
//    }
    
//    public Collection<TmPoint> getBasicForwardPrimers() {
//        return basicForwardPrimers;
//    }    
//
//    public void setBasicForwardPrimers(Collection<TmPoint> basicForwardPrimers) {
//        this.basicForwardPrimers = basicForwardPrimers;
//    }    

    public List<OligoTmPointCalculation> getOligoTmPointCalColl() {
        return oligoTmPointCalColl;
    }

    public void setOligoTmPointCalColl(List<OligoTmPointCalculation> oligoTmPointCalColl) {
        this.oligoTmPointCalColl = oligoTmPointCalColl;
    }    
    
    public String getReversePrimer() {
        return reversePrimer;
    }

    public void setReversePrimer(String reversePrimer) {
        this.reversePrimer = reversePrimer;
    }
    
    public String getReverseTm() {
        return reverseTm;
    }

    public void setReverseTm(String reverseTm) {
        this.reverseTm = reverseTm;
    }
    
    public String getReverseGCPercent() {
        return reverseGCPercent;
    }

    public void setReverseGCPercent(String reverseGCPercent) {
        this.reverseGCPercent = reverseGCPercent;
    }    
    
    @Override
    public String toString() {
        return miRNAName + " | " + miRNAMatureSequencePre + miRNAMatureSequencePost;
    }
    
    @Override
    public boolean equals( Object obj ) {
        
        if( !(obj instanceof Oligo ))
            return false;
        
        Oligo o = (Oligo) obj;
        
        return this.miRNAName.equals( o.getMiRNAName() );
        
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + (this.miRNAName != null ? this.miRNAName.hashCode() : 0);
        hash = 61 * hash + (this.cdNA != null ? this.cdNA.hashCode() : 0);
        return hash;
    }
    
    @Override
    public int compareTo(Oligo o) {
        return this.getMiRNAName().compareTo( o.getMiRNAName() );
    }

}
