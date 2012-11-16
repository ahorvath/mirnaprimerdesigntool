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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mirna.designer.designeralg;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Logger;
import org.mirna.designer.bean.Oligo;
import org.mirna.designer.bean.TmPoint;

/**
 *
 * @author domokosbalint
 */
public class BestMatchFilter {
    protected static Logger logger = Logger.getLogger( "useractivity" );
    
    private final double gcRef;
    private final double tmRef;

    interface ValueGetter {
        double getValue(TmPoint p);
    }

    public BestMatchFilter(double gc, double tm) {
        this.gcRef = gc;
        this.tmRef = tm;
    }

    public void filterBestMatch(OligoTmPointCalculation calc) {
        TmPoint best = analyze(calc);
        removeAllButBest(calc, best);
    }

    boolean isAllPolyt(Collection<TmPoint> forwardPrimers) {
        for(TmPoint p: forwardPrimers) {
            if(! p.getSequence().startsWith("TTT")) {
                return false;
            }
        }
        return true;
    }

    public void filterPolyT(Collection<TmPoint> forwardPrimers) {
        Collection<TmPoint> toRemove = new ArrayList<TmPoint>();
        if(isAllPolyt(forwardPrimers)) {
            return;
        }
        for(TmPoint p: forwardPrimers) {
            if(p.getSequence().startsWith("TTT")) {
                toRemove.add(p);
            }
        }
        for(TmPoint p: toRemove) {
            forwardPrimers.remove(p);
        }
    }

    double calcMax(OligoTmPointCalculation c, ValueGetter v, double refval) {
        double currMax = currMax = refval;
        for (TmPoint p : c.getForwardPrimers()) {
            if (currMax < v.getValue(p)) {
                currMax = v.getValue(p);
            }
        }
        return currMax;
    }

    double calcMin(OligoTmPointCalculation c, ValueGetter v, double refval) {
        double currMin = refval;
        for (TmPoint p : c.getForwardPrimers()) {
            if (currMin > v.getValue(p)) {
                currMin = v.getValue(p);
            }
        }
        return currMin;
    }

    double calcSum(OligoTmPointCalculation c, ValueGetter v, double refval) {
        double sum = refval;
        for (TmPoint p : c.getForwardPrimers()) {
            sum+= v.getValue(p);
        }
        return sum;
    }

    double getScore(double gcMin, double tmMin, double gcSpread, double tmSpread, double gcRef, double tmRef, TmPoint p) {
        double gcNorm = (p.getGcPercent()-gcMin)/gcSpread;
        double tmNorm = (p.getTmPoint()-tmMin)/tmSpread;
        double refGcNorm = (gcRef-gcMin)/gcSpread;
        double refTmNorm = (tmRef-tmMin)/tmSpread;
        return Math.sqrt((gcNorm-refGcNorm)*(gcNorm-refGcNorm)+(tmNorm-refTmNorm)*(tmNorm-refTmNorm));
    }

    TmPoint analyze(OligoTmPointCalculation calc) {
        ValueGetter gcGetter = new ValueGetter() {

            @Override
            public double getValue(TmPoint p) {
                return p.getGcPercent();
            }
        };
        ValueGetter tmGetter = new ValueGetter() {

            @Override
            public double getValue(TmPoint p) {
                return p.getTmPoint();
            }
        };
        double tmpGcMin = calcMin(calc, gcGetter, gcRef);
        double tmpGcMax = calcMax(calc, gcGetter, gcRef);
        double tmpTmMin = calcMin(calc, tmGetter, tmRef);
        double tmpTmMax = calcMax(calc, tmGetter, tmRef);
        double tmpGcSum = calcSum(calc, gcGetter, gcRef);
        double tmpTmSum = calcSum(calc, tmGetter, tmRef);

        double gcSpread = tmpGcMax - tmpGcMin;
        if(Double.compare(0.0, gcSpread ) == 0) {
            gcSpread = 1.0;
        }
        double tmSpread = tmpTmMax - tmpTmMin;
        if(Double.compare(0.0, tmSpread ) == 0) {
            tmSpread = 1.0;
        }

        double bestScore = Double.MAX_VALUE;
        TmPoint best = null;
        for (TmPoint p : calc.getForwardPrimers()) {
            double score = getScore(tmpGcMin, tmpTmMin, gcSpread, tmSpread, gcRef, tmRef, p);
            if(bestScore > score) {
                bestScore = score;
                best = p;
            }
        }
        return best;
        
    }

    void removeAllButBest(OligoTmPointCalculation calc, TmPoint best) {
        Collection<TmPoint> toRemove = new ArrayList<TmPoint>();
        for(TmPoint p : calc.getForwardPrimers()) {
            if(!p.equals(best)) {
                toRemove.add(p);
            }
        }
        calc.getForwardPrimers().removeAll(toRemove);
    }

    String printCalc(Oligo o, OligoTmPointCalculation calc) {
        StringBuilder result = new StringBuilder();
        result.append("calc: ").append(calc.getCalcName()).append( " GC%=").
                append( o.getReverseGCPercent()).append( " TM=").
                append( calc.getReversePrimTmPoint()).append("\n");
        Collection<TmPoint> primers = calc.getForwardPrimers();
        for (TmPoint primer : primers) {
            result.append("\t").append( primer.toString()).append("\n");
        }
        return result.toString();
    }
    
}
