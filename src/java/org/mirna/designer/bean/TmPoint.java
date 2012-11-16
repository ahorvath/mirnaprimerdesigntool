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
package org.mirna.designer.bean;

import java.util.logging.Logger;

public class TmPoint implements Comparable<TmPoint> {
    protected static Logger logger = Logger.getLogger( "useractivity" );

    private double deviation;
    
    private String sequence;    
    private double tmPoint;
    private double gcPercent;
    
    private String tmPointS;
    private String gcPercentS;
    
    public TmPoint(){}

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public double getTmPoint() {
        return tmPoint;
    }

    public void setTmPoint(double tmPoint) {
        this.tmPoint = tmPoint;
    }
    
    public double getDeviation() {
        return deviation;
    }

    public void setDeviation(double deviation) {
        this.deviation = deviation;
    }    

    public double getGcPercent() {
        return gcPercent;
    }

    public void setGcPercent(double gcPercent) {
        this.gcPercent = gcPercent;
    }
    
    public String getTmPointS() {
        return tmPointS;
    }

    public void setTmPointS(String tmPointS) {
        this.tmPointS = tmPointS;
    }

    public String getGcPercentS() {
        return gcPercentS;
    }

    public void setGcPercentS(String gcPercentS) {
        this.gcPercentS = gcPercentS;
    }    
    
    public boolean isBetter( TmPoint tmPoint, int minNucNum, double revPrimTm  ) {
        
        if( this.sequence.length() < minNucNum )
            return false;
        
        if( tmPoint.getSequence().length() < minNucNum )
            return true;
       
        double tmPointRange = Math.abs( revPrimTm - tmPoint.getTmPoint() );
        double thisTmPointRange = Math.abs( revPrimTm - this.tmPoint );
        
        if( (thisTmPointRange < tmPointRange) )
            return true;
        
        if( (thisTmPointRange == tmPointRange) && (this.gcPercent > tmPoint.getGcPercent()) )
            return true;
        
        return false;
    }
    
    @Override
    public String toString() {
        return this.sequence + " " + this.gcPercent+ "% " + this.tmPoint + "°C";
    }
    
    @Override
    public boolean equals( Object obj ) {
        if (obj == this)
        {
            return true;
        }
        if( !(obj instanceof TmPoint) ) {
            return false;
        }
        TmPoint tm = (TmPoint)obj;
        return getSequence().equals( tm.getSequence() );
    }

    @Override
    public int hashCode() {
//        int hash = 7;
//        hash = 53 * hash + (this.sequence != null ? this.sequence.hashCode() : 0);
//        hash = 53 * hash + (int) (Double.doubleToLongBits(this.tmPoint) ^ (Double.doubleToLongBits(this.tmPoint) >>> 32));
//        hash = 53 * hash + (int) (Double.doubleToLongBits(this.gcPercent) ^ (Double.doubleToLongBits(this.gcPercent) >>> 32));
//        return hash;
        return getSequence().hashCode();
    }

    @Override
    public int compareTo(TmPoint o) {
        
        int result = 0;
        
//        double tm = this.tmPoint - o.getTmPoint();
        double tm = this.deviation - o.getDeviation();
        
        if( tm < 0 )
            result = -1;
        
        else if( tm > 0 )
            result = 1;
        
        return result;
    }

}
