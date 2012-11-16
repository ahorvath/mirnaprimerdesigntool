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
import org.mirna.bean.interfaces.MatureMiRNAInterface;

/**
 *
 * @author Attila
 */
@MappedSuperclass
public abstract class MatureMiRNA<T extends MiRNA> implements Serializable, MatureMiRNAInterface<T>, Comparable {
    
    @Column(name="ACCESSION")
    private String accession;
    
    @Column(name="MATURE_MIRNA_ID")
    private String matureMiRNAID;
    
    @Column(name="MATURE_SEQ")
    private String matureSeq;
    
    public MatureMiRNA() {}

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getMatureMiRNAID() {
        return matureMiRNAID;
    }

    public void setMatureMiRNAID(String matureMiRNAID) {
        this.matureMiRNAID = matureMiRNAID;
    }

    public String getMatureSeq() {
        return matureSeq;
    }

    public void setMatureSeq(String matureSeq) {
        this.matureSeq = matureSeq;
    }
    
    @Override
    public String toString() {
        return matureMiRNAID + "|" + accession + "|" + matureSeq;
    }

    @Override
    public boolean equals(Object obj) {
        if( !(obj instanceof MatureMiRNA) )
            return false;
        
        MatureMiRNA mm = (MatureMiRNA) obj;
        
        return this.matureMiRNAID.equals( mm.getMatureMiRNAID() );
    }
    

    @Override
    public int compareTo(Object obj) {        
        MatureMiRNA mm = (MatureMiRNA) obj;
        
        return this.matureMiRNAID.compareTo( mm.getMatureMiRNAID() );
    }    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.matureMiRNAID != null ? this.matureMiRNAID.hashCode() : 0);
        hash = 89 * hash + (this.matureSeq != null ? this.matureSeq.hashCode() : 0);
        return hash;
    }

}
