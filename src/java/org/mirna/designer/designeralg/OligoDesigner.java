package org.mirna.designer.designeralg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import org.apache.log4j.Logger;
import org.mirna.designer.bean.Oligo;
import org.mirna.designer.bean.TmPoint;
import org.mirna.bean.mirna.MatureMiRNA;
import org.mirna.bean.mirna.MiRNA;
import org.mirna.control.DataControl;

/**
 *
 * @author Attila
 */
public class OligoDesigner {

//    private Collection<String> miRNANames;
    protected static Logger logger = Logger.getLogger("useractivity");
    private Collection<MiRNA> miRNAs;
    private String species;
    private int tmPointCount = 5;

    public OligoDesigner( /*String[] miRNANames,*/String species, Collection<MiRNA> miRNAs) {

//        this.miRNANames = Arrays.asList( miRNANames );
        this.miRNAs = miRNAs;
        this.species = species;

    }

    public Collection<Oligo> designOligo() throws Exception {

        Collection<Oligo> result = new ArrayList<Oligo>();

        MiRNADesigner miRNADesigner = null;

        OligoFactory oligoFactory = new OligoFactory();

        for (MiRNA miRNA : miRNAs) {

            for (Object obj : miRNA.getMatureMiRNAColl()) {

                MatureMiRNA matureMiRNA = (MatureMiRNA) obj;

                String miRNASequence = matureMiRNA.getMatureSeq();

                TreeMap<String, List<String>> mms = new DataControl().loadMiRNAToCondition(species, miRNASequence);

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

                oligo.setMiRNAName(miRNA.getMiRNAName());
                oligo.setMiRNAVariantName(matureMiRNA.getMatureMiRNAID());
                oligo.setMiRNALink(miRNA.getMiRNALink());

                // Do filtering to show only the best results
                BestMatchFilter filter;
                filter = new BestMatchFilter(parseDoubleLocalizedString(oligo.getReverseGCPercent()),
                        parseDoubleLocalizedString(basicTmPointCalc.getReversePrimTmPoint()));
                logger.info("Before filter:");
                logger.info(filter.printCalc(oligo, basicTmPointCalc));
                filter.filterPolyT(basicTmPointCalc.getForwardPrimers());
                filter.filterBestMatch(basicTmPointCalc);
                logger.info("After filter:");
                logger.info(filter.printCalc(oligo, basicTmPointCalc));

                filter = new BestMatchFilter(parseDoubleLocalizedString(oligo.getReverseGCPercent()),
                        parseDoubleLocalizedString(baseStackingTmPointCalc.getReversePrimTmPoint()));
                logger.info("Before filter:");
                logger.info(filter.printCalc(oligo, baseStackingTmPointCalc));
                filter.filterPolyT(baseStackingTmPointCalc.getForwardPrimers());
                logger.info("After polyt filter:");
                logger.info(filter.printCalc(oligo, baseStackingTmPointCalc));
                filter.filterBestMatch(baseStackingTmPointCalc);
                logger.info("After bestmatch filter:");
                logger.info(filter.printCalc(oligo, baseStackingTmPointCalc));
                result.add(oligo);
            }

        }

        return result;

    }

    private double parseDoubleLocalizedString(String str) {
        return Double.parseDouble(str.replaceAll(",", "."));
    }
}
