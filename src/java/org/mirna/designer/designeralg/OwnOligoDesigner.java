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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import org.mirna.designer.bean.Oligo;
import org.mirna.designer.bean.TmPoint;

/**
 *
 * @author Attila
 */
public class OwnOligoDesigner {

    private static Logger logger2 = Logger.getLogger("useractivity");
    private int primerCollSize;
    private int tmPointCount = 5;

    public OwnOligoDesigner(String usname, int primerCollSize) {

        this.primerCollSize = primerCollSize;
        logger2.info(usname + "-own-count:" + this.primerCollSize);

    }

    public Collection<Oligo> ownDesign(String sequence) {

        Collection<Oligo> result = new ArrayList<Oligo>();

        OligoFactory oligoFactory = new OligoFactory();

        int i = 1;

        StringTokenizer tokenizer = new StringTokenizer(sequence, "\n");

        MiRNADesigner miRNADesigner = null;

        while (tokenizer.hasMoreTokens()) {

            String miRNASequence = tokenizer.nextToken().toUpperCase();

            Oligo oligo = oligoFactory.createOligo(miRNASequence);

            String reversePrimer = OligoFactory.getReversePrimer();
            String seqPrefix = oligoFactory.getSeqPrefix();

            /*basic calculation ->*/
            miRNADesigner = new BasicMiRNADesigner(tmPointCount, reversePrimer);
            List<TmPoint> basicTmPointList = miRNADesigner.design(seqPrefix);

            OligoTmPointCalculation basicTmPointCalc = new OligoTmPointCalculation();
            basicTmPointCalc.setCalcName("Basic");
            basicTmPointCalc.setReversePrimTmPoint(miRNADesigner.getReverseTm());
            basicTmPointCalc.setForwardPrimers(basicTmPointList);
            /*<- basic calculation*/

            /*base stacking calculation ->*/
            miRNADesigner = new BaseStackingMiRNADesigner(tmPointCount, reversePrimer);
            List<TmPoint> baseStackingTmPointList = miRNADesigner.design(seqPrefix);

            OligoTmPointCalculation baseStackingTmPointCalc = new OligoTmPointCalculation();
            baseStackingTmPointCalc.setCalcName("Base-Stacking");
            baseStackingTmPointCalc.setReversePrimTmPoint(miRNADesigner.getReverseTm());
            baseStackingTmPointCalc.setForwardPrimers(baseStackingTmPointList);

            /*<- base stacking calculation*/

            /*add Tm point calculations ->*/
            oligo.getOligoTmPointCalColl().add(basicTmPointCalc);
            oligo.getOligoTmPointCalColl().add(baseStackingTmPointCalc);
            Collections.sort(oligo.getOligoTmPointCalColl());
            /*<- add Tm point calculations*/

            oligo.setMiRNAName("Own-MiRNA-" + i++);
            oligo.setMiRNAVariantName("Own-MiRNA-" + i++);
            oligo.setMiRNALink("own");

            // Do filtering to show only the best results
            BestMatchFilter filter;
            filter = new BestMatchFilter(parseDoubleLocalizedString(oligo.getReverseGCPercent()),
                    parseDoubleLocalizedString(basicTmPointCalc.getReversePrimTmPoint()));
            logger2.info("Before filter:");
            logger2.info(filter.printCalc(oligo, basicTmPointCalc));
            filter.filterPolyT(basicTmPointCalc.getForwardPrimers());
            filter.filterBestMatch(basicTmPointCalc);
            logger2.info("After filter:");
            logger2.info(filter.printCalc(oligo, basicTmPointCalc));

            filter = new BestMatchFilter(parseDoubleLocalizedString(oligo.getReverseGCPercent()),
                    parseDoubleLocalizedString(baseStackingTmPointCalc.getReversePrimTmPoint()));
            logger2.info("Before filter:");
            logger2.info(filter.printCalc(oligo, baseStackingTmPointCalc));
            filter.filterPolyT(baseStackingTmPointCalc.getForwardPrimers());
            filter.filterBestMatch(baseStackingTmPointCalc);
            logger2.info("After filter:");
            logger2.info(filter.printCalc(oligo, baseStackingTmPointCalc));

            result.add(oligo);

        }

        return result;
    }

    private double parseDoubleLocalizedString(String str) {
        return Double.parseDouble(str.replaceAll(",", "."));
    }
}
