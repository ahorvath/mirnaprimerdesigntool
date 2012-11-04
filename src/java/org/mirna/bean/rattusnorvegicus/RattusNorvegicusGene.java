package org.mirna.bean.rattusnorvegicus;

import java.io.Serializable;
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
@Table(name="RATTUSNORVEGICUS_GENE")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="ID")
public class RattusNorvegicusGene extends Gene<RattusNorvegicusMiRNAInfo> implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", updatable=false, nullable=false)
    private Long id;
    
    @ManyToMany(
     mappedBy="geneColl",
     cascade={CascadeType.PERSIST,CascadeType.MERGE},
     targetEntity=org.mirna.bean.rattusnorvegicus.RattusNorvegicusMiRNAInfo.class
    )
    private Collection<RattusNorvegicusMiRNAInfo> miRNAInfoColl = new ArrayList<RattusNorvegicusMiRNAInfo>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Collection<RattusNorvegicusMiRNAInfo> getMiRNAInfoColl() {
        return this.miRNAInfoColl;
    }

    public void setMiRNAInfoColl(Collection<RattusNorvegicusMiRNAInfo> miRNAInfoColl) {
        this.miRNAInfoColl = miRNAInfoColl;
    }    

}
