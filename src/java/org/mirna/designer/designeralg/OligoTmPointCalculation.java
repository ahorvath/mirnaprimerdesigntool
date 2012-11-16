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
package org.mirna.designer.designeralg;

import java.util.Collection;
import org.mirna.designer.bean.TmPoint;

/**
 *
 * @author Attila
 */
public class OligoTmPointCalculation implements Comparable<OligoTmPointCalculation> {
    
    private String calcName;
    
    private String reversePrimTmPoint;
    
    private Collection<TmPoint> forwardPrimers;
    
    public OligoTmPointCalculation() {}
    
    public String getCalcName() {
        return calcName;
    }

    public void setCalcName(String calcName) {
        this.calcName = calcName;
    }    

    public String getReversePrimTmPoint() {
        return reversePrimTmPoint;
    }

    public void setReversePrimTmPoint(String reversePrimTmPoint) {
        this.reversePrimTmPoint = reversePrimTmPoint;
    }

    public Collection<TmPoint> getForwardPrimers() {
        return forwardPrimers;
    }

    public void setForwardPrimers(Collection<TmPoint> forwardPrimers) {
        this.forwardPrimers = forwardPrimers;
    }

    @Override
    public int compareTo(OligoTmPointCalculation o) {
        return this.calcName.length() - o.getCalcName().length();
    }

}
