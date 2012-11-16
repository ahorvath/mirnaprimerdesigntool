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
package org.mirna.bean.gene;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import org.mirna.bean.interfaces.GeneInterface;
import org.mirna.bean.mirna.MiRNAInfo;

/**
 *
 * @author Attila
 */
@MappedSuperclass
public abstract class Gene<T extends MiRNAInfo> implements Serializable, Comparable<Gene>, GeneInterface<T> {
    
    @Column(name="geneID")
    private String geneId;
    
    @Column(name="geneFullName")
    private String geneFullName;    
    
    @Column(name="geneSymbol")
    private String geneSymbol = "unidentified";
    
    @Column(name="geneAlternativeSymbol", columnDefinition="text")
    private String geneAlternativeSymbol = "unidentified";
    
    @Column(name="geneStart")
    private Long geneStart;
    
    @Column(name="geneEnd")
    private Long geneEnd;
    
    @Column(name="geneChr")
    private String geneChr;
    
    @Column(name="geneStd")
    private String geneStd;
    
    @Column(name="molecFunc", columnDefinition="text")
    private String molecularFunctions;
    
    @Column(name="biologFunc", columnDefinition="text")
    private String biologicalFunctions;    
    
    @Column(name="transcriptID", columnDefinition="text")
    private String transcriptID;
    
    @Column(name="proteinID", columnDefinition="text")
    private String proteinID;
    
    @Column(name="affymetrixID", columnDefinition="text")
    private String affymetrixID = "unidentified";    
    
    private transient String title;
            
    public Gene() {
        
    }  

    public String getGeneId() {
        return geneId;
    }

    public void setGeneId(String geneId) {
        this.geneId = geneId;
    }
    
    public String getGeneFullName() {
        return geneFullName;
    }

    public void setGeneFullName(String geneFullName) {
        this.geneFullName = geneFullName;
    }    

    public String getGeneSymbol() {
        return geneSymbol;
    }

    public void setGeneSymbol(String geneSymbol) {
        this.geneSymbol = geneSymbol;
    }
    
    public String getGeneAlternativeSymbol() {
        return geneAlternativeSymbol;
    }

    public void setGeneAlternativeSymbol(String geneAlternativeSymbol) {
        this.geneAlternativeSymbol = geneAlternativeSymbol;
    }    

    public Long getGeneStart() {
        return geneStart;
    }

    public void setGeneStart(Long geneStart) {
        this.geneStart = geneStart;
    }

    public Long getGeneEnd() {
        return geneEnd;
    }

    public void setGeneEnd(Long geneEnd) {
        this.geneEnd = geneEnd;
    }

    public String getGeneChr() {
        return geneChr;
    }

    public void setGeneChr(String geneChr) {
        this.geneChr = geneChr;
    }
    
    public String getGeneStd() {
        return geneStd;
    }

    public void setGeneStd(String geneStd) {
        this.geneStd = geneStd;
    }    
    
    public String getTranscriptID() {
        return transcriptID;
    }

    public void setTranscriptID(String transcriptID) {
        this.transcriptID = transcriptID;
    }

    public String getProteinID() {
        return proteinID;
    }

    public void setProteinID(String proteinID) {
        this.proteinID = proteinID;
    }
    
    public String getAffymetrixID() {
        return affymetrixID;
    }

    public void setAffymetrixID(String affymetrixID) {
        this.affymetrixID = affymetrixID;
    }    
    
    public String getMolecularFunctions() {
        return molecularFunctions;
    }

    public void setMolecularFunctions(String molecularFunctions) {
        this.molecularFunctions = molecularFunctions;
    }

    public String getBiologicalFunctions() {
        return biologicalFunctions;
    }

    public void setBiologicalFunctions(String biologicalFunctions) {
        this.biologicalFunctions = biologicalFunctions;
    }
    
    public String getTitle() {
        return "GeneID:" + geneId + " | Pos:" + geneStart.toString() + " - " + geneEnd.toString() + " | Chr:" + geneChr;
    }
    
    @Override
    public int compareTo(Gene o) {
        return this.geneId.compareTo( o.getGeneId() );
    }    
    
    @Override
    public String toString() {
        return geneId + " | " +
               geneFullName + " | " +
               geneSymbol + " | " +
               geneAlternativeSymbol + " | " +
               geneStart + " | " +
               geneEnd + " | " +
               geneChr + " | " +
               transcriptID + " | " +
               proteinID + " | " +
               molecularFunctions + " | " +
               biologicalFunctions;        
    }
    
}