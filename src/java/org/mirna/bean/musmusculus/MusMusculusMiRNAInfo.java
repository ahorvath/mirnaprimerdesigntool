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
