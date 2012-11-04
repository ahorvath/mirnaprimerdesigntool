package org.mirna.bean.drosophila;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.mirna.bean.mirna.MiRNA;

/**
 *
 * @author Attila
 */
@Entity
@Table(name = "DROSOPHILA_MIRNA")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="ID")
public class DrosophilaMiRNA extends MiRNA<DrosophilaMiRNAInfo,DrosophilaMatureMiRNA> implements java.io.Serializable {
    
    public static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", updatable=false, nullable=false)
    private Long id;
    
    @OneToMany(
     mappedBy = "miRNA",
     cascade = {CascadeType.PERSIST},
     fetch = FetchType.LAZY
    )
    private List<DrosophilaMiRNAInfo> miRNAInfoColl = new ArrayList<DrosophilaMiRNAInfo>();
    
    @OneToMany(
     mappedBy = "miRNA",
     cascade = {CascadeType.PERSIST},
     fetch = FetchType.LAZY
    )
    private List<DrosophilaMatureMiRNA> matureMiRNAColl = new ArrayList<DrosophilaMatureMiRNA>();    
    
    public DrosophilaMiRNA() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public List<DrosophilaMiRNAInfo> getMiRNAInfoColl() {
        return this.miRNAInfoColl;
    }

    @Override
    public void setMiRNAInfoColl(List<DrosophilaMiRNAInfo> miRNAInfoColl) {
        this.miRNAInfoColl = miRNAInfoColl;
    }

    @Override
    public List<DrosophilaMatureMiRNA> getMatureMiRNAColl() {
        return this.matureMiRNAColl;
    }

    @Override
    public void setMatureMiRNAColl(List<DrosophilaMatureMiRNA> matureMiRNAColl) {
        this.matureMiRNAColl = matureMiRNAColl;
    }

}
