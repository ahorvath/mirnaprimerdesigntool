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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mirna.designer.bean.Oligo;
import org.mirna.designer.bean.TmPoint;

/**
 *
 * @author domokosbalint
 */
public class OwnOligoDesignerTest {

    TmPoint tm1;
    TmPoint tm2;
    TmPoint tm3;
    OligoTmPointCalculation calc1;
    OligoTmPointCalculation calc2;
    Oligo o;
    OwnOligoDesigner designer;

    @Before
    public void setup() throws Exception {

        designer = new OwnOligoDesigner("test", 10);

        Collection<Oligo> coll = designer.ownDesign("ACGUGUUUGACAUAUUUAGCAAUACGUGUUUGACAUAUUUAGCAAUACGUGUUUGA");
        o = coll.iterator().next();

        List<OligoTmPointCalculation> calcList = o.getOligoTmPointCalColl();
        calc1 = calcList.get(0);
        calc2 = calcList.get(1);
    }

    

    String[] toArray(Collection<TmPoint> coll) {
        List<String> result = new ArrayList<String>();
        for (TmPoint p : coll) {
            result.add(p.getSequence());
        }
        return result.toArray(new String[]{});
    }

    String join(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }
        String sep = ",";
        StringBuilder builder = new StringBuilder();
        for (String s : arr) {
            builder.append(s).append(sep);
        }
        builder.delete(builder.length() - sep.length(), builder.length());
        return builder.toString();
    }

    @Test
    public void testJoin() {
        assertEquals("a,b,c", join(new String[]{"a", "b", "c"}));
    }

//    original result, before filtering
//    @Test
//    public void testPrintCalc1() {
//        System.out.println(printCalc(o, calc1));
//
//        assertEquals(join(new String[]{
//                    "GTTACGTGTTTGACATATTTAGCAA",
//                    "TGTACGTGTTTGACATATTTAGCAA",
//                    "TTGACGTGTTTGACATATTTAGCAA",
//                    "TTTTTTACGTGTTTGACATATTTAGCAA",
//                    "TTTTTTTACGTGTTTGACATATTTAGCAA"
//                }), join(toArray(calc1.getForwardPrimers())));
//        System.out.println(printCalc(o, calc2));
//
//        assertEquals(join(new String[]{
//                    "GGTGTGTTTACGTGTTTGACATATTTAG",
//                    "GTGGTGTTTACGTGTTTGACATATTTAG",
//                    "GTGTGGTTTACGTGTTTGACATATTTAG",
//                    "GGTGTTGTTACGTGTTTGACATATTTAG",
//                    "GTGGTTGTTACGTGTTTGACATATTTAG"
//                }), join(toArray(calc2.getForwardPrimers())));
//    }

    @Test
    public void filterPolyT_removes_polyt() {
        OligoTmPointCalculation testCalc = new OligoTmPointCalculation();
        ArrayList<TmPoint> fp = new ArrayList<TmPoint>();
        TmPoint p = new TmPoint();
        p.setSequence("GTTACGTGTTTGACATATTTAGCAA");
        fp.add(p);
        p = new TmPoint();
        p.setSequence("TTTACGTGTTTGACATATTTAGCAA");
        fp.add(p);
        p = new TmPoint();
        p.setSequence("TTTTACGTGTTTGACATATTTAGCAA");
        fp.add(p);
        testCalc.setForwardPrimers(fp);
        new BestMatchFilter(0.0, 0.0).filterPolyT(testCalc.getForwardPrimers());
        assertEquals(join(new String[]{
                    "GTTACGTGTTTGACATATTTAGCAA"
                }), join(toArray(testCalc.getForwardPrimers())));
    }

     @Test
    public void filterPolyT_leave_if_all_polyt() {
        OligoTmPointCalculation testCalc = new OligoTmPointCalculation();
        ArrayList<TmPoint> fp = new ArrayList<TmPoint>();
        TmPoint p = new TmPoint();
        p.setSequence("TTTTTACGTGTTTGACATATTTAGCAA");
        fp.add(p);
        p = new TmPoint();
        p.setSequence("TTTACGTGTTTGACATATTTAGCAA");
        fp.add(p);
        p = new TmPoint();
        p.setSequence("TTTTACGTGTTTGACATATTTAGCAA");
        fp.add(p);
        testCalc.setForwardPrimers(fp);
        new BestMatchFilter(0.0, 0.0).filterPolyT(testCalc.getForwardPrimers());
        assertEquals(join(new String[]{
                    "TTTTTACGTGTTTGACATATTTAGCAA",
                    "TTTACGTGTTTGACATATTTAGCAA",
                    "TTTTACGTGTTTGACATATTTAGCAA"
                }), join(toArray(testCalc.getForwardPrimers())));
    }

    private OligoTmPointCalculation createTestCalc() {
        OligoTmPointCalculation testCalc = new OligoTmPointCalculation();
        tm1 = new TmPoint();
        tm1.setGcPercentS("52.0");
        tm1.setTmPointS("21.0");
        tm1.setGcPercent(52.0);
        tm1.setTmPoint(21.0);
        tm2 = new TmPoint();
        tm2.setGcPercentS("51.0");
        tm2.setTmPointS("20.0");
        tm2.setGcPercent(51.0);
        tm2.setTmPoint(20.0);
        tm3 = new TmPoint();
        tm3.setGcPercentS("41.0");
        tm3.setTmPointS("18.0");
        tm3.setGcPercent(41.0);
        tm3.setTmPoint(18.0);
        testCalc.setForwardPrimers(Arrays.asList(tm1, tm2, tm3));
        return testCalc;
    }

    @Test
    public void testGetScore_1() {
        OligoTmPointCalculation testCalc = createTestCalc();
        BestMatchFilter filter = new BestMatchFilter(0.0, 0.0);
        double score = filter.getScore(20.0, 40.0, 10.0, 20.0, 30.0, 45.0, tm1);
        assertEquals(2.506, score, 0.0001);
    }

    @Test
    public void testGetScore_2() {
        OligoTmPointCalculation testCalc = createTestCalc();
        BestMatchFilter filter = new BestMatchFilter(0.0, 0.0);
        double score = filter.getScore(20.0, 40.0, 10.0, 20.0, 30.0, 45.0, tm2);
        assertEquals(2.4438, score, 0.0001);
    }

        @Test
    public void testGetScore_3() {
        OligoTmPointCalculation testCalc = createTestCalc();
        BestMatchFilter filter = new BestMatchFilter(0.0, 0.0);
        double score = filter.getScore(20.0, 40.0, 10.0, 20.0, 30.0, 45.0, tm3);
        assertEquals(1.7414, score, 0.0001);
    }


    @Test
    public void testAnalyze_1() {
        OligoTmPointCalculation testCalc = createTestCalc();
        BestMatchFilter filter = new BestMatchFilter(41.0, 19.0);
        TmPoint best = filter.analyze(testCalc);
        assertSame(tm3, best);
    }

    @Test
    public void testAnalyze_2() {
        OligoTmPointCalculation testCalc = createTestCalc();
        BestMatchFilter filter = new BestMatchFilter(52.0, 22.0);
        TmPoint best = filter.analyze(testCalc);
        assertSame(tm1, best);
    }

    @Test
    public void filterBestMatch() {
        new BestMatchFilter(68.75, 51.06).filterBestMatch(calc1);
        assertEquals(join(new String[]{
                    "GTTACGTGTTTGACATATTTAGCAA"
       }), join(toArray(calc1.getForwardPrimers())));
    }

    @Test
    public void testPrintCalc2() {
        BestMatchFilter filter;
        filter = new BestMatchFilter(68.75, 51.06);
        filter.filterPolyT(calc1.getForwardPrimers());
        filter.filterBestMatch(calc1);
        System.out.println(filter.printCalc(o, calc1));
        assertEquals(join(new String[]{
                    "GTTACGTGTTTGACATATTTAGCAA"
                }), join(toArray(calc1.getForwardPrimers())));

        filter = new BestMatchFilter(68.75, 55.89);
        filter.filterPolyT(calc2.getForwardPrimers());
        filter.filterBestMatch(calc2);
        System.out.println(filter.printCalc(o, calc2));
        assertEquals(join(new String[]{
                    "GGTGTGTTTACGTGTTTGACATATTTAG"
                }), join(toArray(calc2.getForwardPrimers())));
    }

}
