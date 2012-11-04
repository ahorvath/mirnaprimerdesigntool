package org.mirna.bean.drosophila;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.mirna.bean.gene.Gene;

/**
 *
 * @author Attila
 */
@Entity
@Table(name="DROSOPHILA_GENE")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="ID")
public class DrosophilaGene extends Gene<DrosophilaMiRNAInfo> implements java.io.Serializable {
    
    public static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", updatable=false, nullable=false)
    private Long id;
    
    @ManyToMany(
     mappedBy="geneColl",
     cascade={CascadeType.PERSIST,CascadeType.MERGE},
     targetEntity=org.mirna.bean.drosophila.DrosophilaMiRNAInfo.class
    )
    private Collection<DrosophilaMiRNAInfo> miRNAInfoColl = new ArrayList<DrosophilaMiRNAInfo>();    
    
    public DrosophilaGene() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<DrosophilaMiRNAInfo> getMiRNAInfoColl() {
        return this.miRNAInfoColl;
    }

    public void setMiRNAInfoColl(Collection<DrosophilaMiRNAInfo> miRNAInfoColl) {
        this.miRNAInfoColl = miRNAInfoColl;
    }

}
