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
