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
package org.mirna.bean.mirna;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.Index;
import org.mirna.bean.interfaces.MiRNAInterface;

/**
 *
 * @author Attila
 */
@MappedSuperclass
public abstract class MiRNA<T extends MiRNAInfo, P extends MatureMiRNA> implements Serializable, Comparable<MiRNA>, MiRNAInterface<T,P> {
    
    @Column(name="miRNANAme")
    private String miRNAName;
    
    @Column(name="ACCESSION")
    private String accession;    
    
    @Column(name="miRNALink")
    private String miRNALink;
    
    @Column(name="intergenic")
    private String intergenic;
    
    @Column(name="seq")
    private String seq;
    
    public MiRNA() {
        
    }

    public String getMiRNAName() {
        return miRNAName;
    }

    public void setMiRNAName(String miRNAName) {
        this.miRNAName = miRNAName;
    }
    
    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }    
    
    public String getMiRNALink() {
        return miRNALink;
    }

    public void setMiRNALink(String miRNALink) {
        this.miRNALink = miRNALink;
    }    

    public String getIntergenic() {
        return intergenic;
    }

    public void setIntergenic(String intergenic) {
        this.intergenic = intergenic;
    }
    
    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    } 
  
    @Override
    public String toString() {
        return miRNAName + " | " + miRNALink + " | " + intergenic + "|" + seq;
    }

    @Override
    public int compareTo(MiRNA o) {
        return this.miRNAName.compareTo( o.getMiRNAName() );
    }

}
