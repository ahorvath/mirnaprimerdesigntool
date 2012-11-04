package org.mirna.bean.musmusculus;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.mirna.bean.mirna.MiRNAInfo;

/**
 *
 * @author Attila
 */
@Entity
@Table(name = "MUSMUSCULUS_MiRNAINFO")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="ID")
public class MusMusculusMiRNAInfo extends MiRNAInfo<MusMusculusMiRNA,MusMusculusGene> implements java.io.Serializable {
    
    public static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", updatable=false, nullable=false)
    private Long id;
    
    @ManyToOne(
     cascade = {CascadeType.PERSIST}, 
     fetch = FetchType.LAZY
    )
    @JoinColumn(name = "musmusculus_mirnaid")
    private MusMusculusMiRNA miRNA;
    
    @ManyToMany(
     targetEntity=org.mirna.bean.musmusculus.MusMusculusGene.class,
     cascade={CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinTable(
     name="musmusculus_mirnaInfo_gene",
     joinColumns=@JoinColumn(name="mirnainfo_id"),
     inverseJoinColumns=@JoinColumn(name="gene_id")
    )
    private Collection<MusMusculusGene> geneColl = new ArrayList<MusMusculusGene>();    
    
    public MusMusculusMiRNAInfo() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public MusMusculusMiRNA getMiRNA() {
        return miRNA;
    }

    @Override
    public void setMiRNA(MusMusculusMiRNA miRNA) {
        this.miRNA = miRNA;
    }

    @Override
    public Collection<MusMusculusGene> getGeneColl() {
        return this.geneColl;
    }

    @Override
    public void setGeneColl(Collection<MusMusculusGene> geneColl) {
        this.geneColl = geneColl;
    }

}
