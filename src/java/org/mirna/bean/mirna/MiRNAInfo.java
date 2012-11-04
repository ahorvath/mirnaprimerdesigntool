package org.mirna.bean.mirna;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import org.mirna.bean.gene.Gene;
import org.mirna.bean.interfaces.MiRNAInfoInterface;

/**
 *
 * @author Attila
 */
@MappedSuperclass
public abstract class MiRNAInfo<T extends MiRNA, P extends Gene> implements Serializable, MiRNAInfoInterface<T,P> {
    
    @Column(name="miRNAChr")
    private String miRNAChr;
    
    @Column(name="miRNAStart")
    private Long miRNAStart;
    
    @Column(name="miRNAEnd")
    private Long miRNAEnd;
    
    @Column(name="standard")
    private String standard;
    
    public MiRNAInfo() {
        
    }  

    public String getMiRNAChr() {
        return miRNAChr;
    }

    public void setMiRNAChr(String miRNAChr) {
        this.miRNAChr = miRNAChr;
    }

    public Long getMiRNAStart() {
        return miRNAStart;
    }

    public void setMiRNAStart(Long miRNAStart) {
        this.miRNAStart = miRNAStart;
    }

    public Long getMiRNAEnd() {
        return miRNAEnd;
    }

    public void setMiRNAEnd(Long miRNAEnd) {
        this.miRNAEnd = miRNAEnd;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    } 
    
    @Override
    public String toString() {
        return miRNAChr + "|" + miRNAStart + "|" + miRNAEnd + "|" + standard;
    }

}
