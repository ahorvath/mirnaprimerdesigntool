package org.mirna.bean.homosapiens;

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
import org.hibernate.annotations.IndexColumn;
import org.mirna.bean.mirna.MiRNA;

/**
 *
 * @author Attila
 */
@Entity
@Table(name = "HOMOSAPIENS_MIRNA")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="ID")
public class HomoSapiensMiRNA extends MiRNA<HomoSapiensMiRNAInfo, HomoSapiensMatureMiRNA> implements java.io.Serializable {
    
    public static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", updatable=false, nullable=false)
    private Long id;    
    
    @OneToMany(
     mappedBy = "miRNA",
     cascade = {CascadeType.ALL},
     fetch = FetchType.LAZY
    )
    private List<HomoSapiensMiRNAInfo> miRNAInfoColl = new ArrayList<HomoSapiensMiRNAInfo>();
    
    
    @OneToMany(
     mappedBy = "miRNA",
     cascade = {CascadeType.ALL},
     fetch = FetchType.LAZY
    )
    private List<HomoSapiensMatureMiRNA> matureMiRNAColl = new ArrayList<HomoSapiensMatureMiRNA>();

    public HomoSapiensMiRNA() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public List<HomoSapiensMiRNAInfo> getMiRNAInfoColl() {
        return this.miRNAInfoColl;
    }

    @Override
    public void setMiRNAInfoColl( List<HomoSapiensMiRNAInfo> miRNAInfoColl ) {
        this.miRNAInfoColl = miRNAInfoColl;
    }

    @Override
    public List<HomoSapiensMatureMiRNA> getMatureMiRNAColl() {
        return this.matureMiRNAColl;
    }

    @Override
    public void setMatureMiRNAColl(List<HomoSapiensMatureMiRNA> matureMiRNAColl) {
        this.matureMiRNAColl = matureMiRNAColl;
    }

}
