package org.mirna.bean.rattusnorvegicus;

import java.io.Serializable;
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
@Table(name = "RATTUSNORVEGICUS_MiRNAINFO")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="ID")
public class RattusNorvegicusMiRNAInfo extends MiRNAInfo<RattusNorvegicusMiRNA, RattusNorvegicusGene> implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", updatable=false, nullable=false)
    private Long id;
    
    @ManyToOne(
     cascade = {CascadeType.PERSIST}, 
     fetch = FetchType.LAZY
    )
    @JoinColumn(name = "rattusnorvegicus_mirnaid")
    private RattusNorvegicusMiRNA miRNA;
    
    @ManyToMany(
     targetEntity=org.mirna.bean.rattusnorvegicus.RattusNorvegicusGene.class,
     cascade={CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinTable(
     name="rattusnorvegicus_mirnaInfo_gene",
     joinColumns=@JoinColumn(name="mirnainfo_id"),
     inverseJoinColumns=@JoinColumn(name="gene_id")
    )
    private Collection<RattusNorvegicusGene> geneColl = new ArrayList<RattusNorvegicusGene>();
    
    public RattusNorvegicusMiRNAInfo() {}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    

    @Override
    public RattusNorvegicusMiRNA getMiRNA() {
        return miRNA;
    }

    @Override
    public void setMiRNA(RattusNorvegicusMiRNA miRNA) {
        this.miRNA = miRNA;
    }

    @Override
    public Collection<RattusNorvegicusGene> getGeneColl() {
        return geneColl;
    }

    @Override
    public void setGeneColl(Collection<RattusNorvegicusGene> geneColl) {
        this.geneColl = geneColl;
    }

}
