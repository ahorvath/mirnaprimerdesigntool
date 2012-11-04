package org.mirna.bean.drosophila;

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
@Table(name = "DROSOPHILA_MiRNAINFO")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="ID")
public class DrosophilaMiRNAInfo extends MiRNAInfo<DrosophilaMiRNA,DrosophilaGene> implements java.io.Serializable {
    
    public static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", updatable=false, nullable=false)
    private Long id;
    
    @ManyToOne(
     cascade = {CascadeType.PERSIST}, 
     fetch = FetchType.LAZY
    )
    @JoinColumn(name = "drosophila_mirnaid")
    private DrosophilaMiRNA miRNA;
    
    @ManyToMany(
     targetEntity=org.mirna.bean.drosophila.DrosophilaGene.class,
     cascade={CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinTable(
     name="drosophila_mirnaInfo_gene",
     joinColumns=@JoinColumn(name="mirnainfo_id"),
     inverseJoinColumns=@JoinColumn(name="gene_id")
    )
    private Collection<DrosophilaGene> geneColl = new ArrayList<DrosophilaGene>();    
    
    public DrosophilaMiRNAInfo() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public DrosophilaMiRNA getMiRNA() {
        return this.miRNA;
    }

    @Override
    public void setMiRNA(DrosophilaMiRNA miRNA) {
        this.miRNA = miRNA;
    }

    @Override
    public Collection<DrosophilaGene> getGeneColl() {
        return this.geneColl;
    }

    @Override
    public void setGeneColl(Collection<DrosophilaGene> geneColl) {
        this.geneColl = geneColl;
    }

}
