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