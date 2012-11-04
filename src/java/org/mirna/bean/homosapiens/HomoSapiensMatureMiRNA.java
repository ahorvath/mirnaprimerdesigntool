package org.mirna.bean.homosapiens;

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
@Table(name = "HOMOSAPIENS_MATURE_MIRNA")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="ID")
public class HomoSapiensMatureMiRNA extends MatureMiRNA<HomoSapiensMiRNA> implements Serializable {
    
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
    
    public HomoSapiensMatureMiRNA() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public HomoSapiensMiRNA getMiRNA() {
        return this.miRNA;
    }

    @Override
    public void setMiRNA(HomoSapiensMiRNA miRNA) {
        this.miRNA = miRNA;
    }

}
