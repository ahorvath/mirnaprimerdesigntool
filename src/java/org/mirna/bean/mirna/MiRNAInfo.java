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
import org.mirna.bean.gene.Gene;
import org.mirna.bean.interfaces.MiRNAInfoInterface;

/**
 *
 * @author Attila
 */
@MappedSuperclass
public abstract class MiRNAInfo<T extends MiRNA, P extends Gene> implements Serializable, MiRNAInfoInterface<T,P> {
    
    @Column(name="miRNAChr")
    private String miRNAChr;
    
    @Column(name="miRNAStart")
    private Long miRNAStart;
    
    @Column(name="miRNAEnd")
    private Long miRNAEnd;
    
    @Column(name="standard")
    private String standard;
    
    public MiRNAInfo() {
        
    }  

    public String getMiRNAChr() {
        return miRNAChr;
    }

    public void setMiRNAChr(String miRNAChr) {
        this.miRNAChr = miRNAChr;
    }

    public Long getMiRNAStart() {
        return miRNAStart;
    }

    public void setMiRNAStart(Long miRNAStart) {
        this.miRNAStart = miRNAStart;
    }

    public Long getMiRNAEnd() {
        return miRNAEnd;
    }

    public void setMiRNAEnd(Long miRNAEnd) {
        this.miRNAEnd = miRNAEnd;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    } 
    
    @Override
    public String toString() {
        return miRNAChr + "|" + miRNAStart + "|" + miRNAEnd + "|" + standard;
    }

}
