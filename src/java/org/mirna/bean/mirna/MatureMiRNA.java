package org.mirna.bean.mirna;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import org.mirna.bean.interfaces.MatureMiRNAInterface;

/**
 *
 * @author Attila
 */
@MappedSuperclass
public abstract class MatureMiRNA<T extends MiRNA> implements Serializable, MatureMiRNAInterface<T>, Comparable {
    
    @Column(name="ACCESSION")
    private String accession;
    
    @Column(name="MATURE_MIRNA_ID")
    private String matureMiRNAID;
    
    @Column(name="MATURE_SEQ")
    private String matureSeq;
    
    public MatureMiRNA() {}

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getMatureMiRNAID() {
        return matureMiRNAID;
    }

    public void setMatureMiRNAID(String matureMiRNAID) {
        this.matureMiRNAID = matureMiRNAID;
    }

    public String getMatureSeq() {
        return matureSeq;
    }

    public void setMatureSeq(String matureSeq) {
        this.matureSeq = matureSeq;
    }
    
    @Override
    public String toString() {
        return matureMiRNAID + "|" + accession + "|" + matureSeq;
    }

    @Override
    public boolean equals(Object obj) {
        if( !(obj instanceof MatureMiRNA) )
            return false;
        
        MatureMiRNA mm = (MatureMiRNA) obj;
        
        return this.matureMiRNAID.equals( mm.getMatureMiRNAID() );
    }
    

    @Override
    public int compareTo(Object obj) {        
        MatureMiRNA mm = (MatureMiRNA) obj;
        
        return this.matureMiRNAID.compareTo( mm.getMatureMiRNAID() );
    }    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.matureMiRNAID != null ? this.matureMiRNAID.hashCode() : 0);
        hash = 89 * hash + (this.matureSeq != null ? this.matureSeq.hashCode() : 0);
        return hash;
    }

}
