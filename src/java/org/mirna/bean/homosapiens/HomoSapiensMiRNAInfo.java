package org.mirna.bean.homosapiens;

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
import org.hibernate.annotations.Index;
import org.mirna.bean.mirna.MiRNAInfo;

/**
 *
 * @author Attila
 */
@Entity
@Table(name = "HOMOSAPIENS_MiRNAINFO")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="ID")
public class HomoSapiensMiRNAInfo extends MiRNAInfo<HomoSapiensMiRNA,HomoSapiensGene> implements java.io.Serializable {
    
    public static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", updatable=false, nullable=false)
    private Long id;    
    
    @ManyToOne(
     cascade = {CascadeType.ALL}, 
     fetch = FetchType.LAZY
    )
    @JoinColumn(name = "homosapiens_mirnaid")
    private HomoSapiensMiRNA miRNA;
    
    @ManyToMany(
     targetEntity=org.mirna.bean.homosapiens.HomoSapiensGene.class,
     cascade={CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinTable(
     name="homosapiens_mirnaInfo_gene",
     joinColumns=@JoinColumn(name="mirnainfo_id"),
     inverseJoinColumns=@JoinColumn(name="gene_id")
    )
    private Collection<HomoSapiensGene> geneColl = new ArrayList<HomoSapiensGene>();

    public HomoSapiensMiRNAInfo() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public HomoSapiensMiRNA getMiRNA() {
        return miRNA;
    }
    
    @Override
    public void setMiRNA(HomoSapiensMiRNA miRNA) {
        this.miRNA = miRNA;
    }

    @Override
    public Collection<HomoSapiensGene> getGeneColl() {
        return this.geneColl;
    }

    @Override
    public void setGeneColl(Collection<HomoSapiensGene> geneColl) {
        this.geneColl = geneColl;
    }
    
}
