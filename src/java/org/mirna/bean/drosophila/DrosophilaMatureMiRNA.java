package org.mirna.bean.drosophila;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.mirna.bean.mirna.MatureMiRNA;

/**
 *
 * @author Attila
 */
@Entity
@Table(name = "DROSOPHILA_MATURE_MIRNA")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="ID")
public class DrosophilaMatureMiRNA extends MatureMiRNA<DrosophilaMiRNA> implements Serializable{
    
    public static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", updatable=false, nullable=false)    
    private Long id;    
    
    @ManyToOne(
     cascade = CascadeType.PERSIST,
     fetch = FetchType.LAZY
    )
    @JoinColumn(name = "drosophila_mirnaid")     
    private DrosophilaMiRNA miRNA;
    
    public DrosophilaMatureMiRNA() {}
    
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

}
