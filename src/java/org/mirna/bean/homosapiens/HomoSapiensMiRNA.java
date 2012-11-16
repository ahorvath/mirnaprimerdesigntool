/*
* The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection. The usergets the best result by two different Tm calculating methods.  The tool designs the miRNA specific sequence of the stem-loop RT primer as well. 
*
* copyright (C) 2009-2012 Astrid Research Ltd. 
* copyright (C) November, 2012 University of Debrecen, Clinical Genomic Center, Medical and Health Science Center, Debrecen, Hungary
*
* The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection.  The usergets the best result by two different Tm calculating methods.  The tool designs the miRNA specific sequence of the stem-loop RT primer as well. 
*
*    miRNA Design Tool is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    miRNA Design Tool is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with miRNA Primer Design Tool.  If not, see <http://www.gnu.org/licenses/>.
*/
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
