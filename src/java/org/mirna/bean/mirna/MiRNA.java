package org.mirna.bean.mirna;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.Index;
import org.mirna.bean.interfaces.MiRNAInterface;

/**
 *
 * @author Attila
 */
@MappedSuperclass
public abstract class MiRNA<T extends MiRNAInfo, P extends MatureMiRNA> implements Serializable, Comparable<MiRNA>, MiRNAInterface<T,P> {
    
    @Column(name="miRNANAme")
    private String miRNAName;
    
    @Column(name="ACCESSION")
    private String accession;    
    
    @Column(name="miRNALink")
    private String miRNALink;
    
    @Column(name="intergenic")
    private String intergenic;
    
    @Column(name="seq")
    private String seq;
    
    public MiRNA() {
        
    }

    public String getMiRNAName() {
        return miRNAName;
    }

    public void setMiRNAName(String miRNAName) {
        this.miRNAName = miRNAName;
    }
    
    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }    
    
    public String getMiRNALink() {
        return miRNALink;
    }

    public void setMiRNALink(String miRNALink) {
        this.miRNALink = miRNALink;
    }    

    public String getIntergenic() {
        return intergenic;
    }

    public void setIntergenic(String intergenic) {
        this.intergenic = intergenic;
    }
    
    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    } 
  
    @Override
    public String toString() {
        return miRNAName + " | " + miRNALink + " | " + intergenic + "|" + seq;
    }

    @Override
    public int compareTo(MiRNA o) {
        return this.miRNAName.compareTo( o.getMiRNAName() );
    }

}
