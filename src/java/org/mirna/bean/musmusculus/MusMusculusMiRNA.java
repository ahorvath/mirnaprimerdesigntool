package org.mirna.bean.musmusculus;

import java.io.Serializable;
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
@Table(name = "MUSMUSCULUS_MIRNA")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="ID")
public class MusMusculusMiRNA extends MiRNA<MusMusculusMiRNAInfo,MusMusculusMatureMiRNA> implements Serializable {
    
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
    private List<MusMusculusMiRNAInfo> miRNAInfoColl = new ArrayList<MusMusculusMiRNAInfo>();
    
    @OneToMany(
     mappedBy = "miRNA",
     cascade = {CascadeType.PERSIST},
     fetch = FetchType.LAZY
    )
    private List<MusMusculusMatureMiRNA> matureMiRNAColl = new ArrayList<MusMusculusMatureMiRNA>();    
    
    public MusMusculusMiRNA() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public List<MusMusculusMiRNAInfo> getMiRNAInfoColl() {
        return this.miRNAInfoColl;
    }

    @Override
    public void setMiRNAInfoColl(List<MusMusculusMiRNAInfo> miRNAInfoColl) {
        this.miRNAInfoColl = miRNAInfoColl;
    }

    @Override
    public List<MusMusculusMatureMiRNA> getMatureMiRNAColl() {
        return this.matureMiRNAColl;
    }

    @Override
    public void setMatureMiRNAColl(List<MusMusculusMatureMiRNA> matureMiRNAColl) {
        this.matureMiRNAColl = matureMiRNAColl;
    }
    
    
}
