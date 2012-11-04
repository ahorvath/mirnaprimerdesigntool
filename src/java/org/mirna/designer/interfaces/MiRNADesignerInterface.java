package org.mirna.designer.interfaces;

import java.util.List;
import org.mirna.designer.bean.TmPoint;

/**
 *
 * @author Attila
 */
public interface MiRNADesignerInterface {
    
    /*set begin mature miRNA Tm point*/
    public String createBeginMatureMiRNA( String seq );
    
    /*desig forward primer*/
    public List<TmPoint> design( String seqPrefix );
    
    /*sets mature miRNA, loop prefix, CDNA*/
//    public Oligo oligoInit( String miRNASequence );
    
    /*depth searcher*/
    public TmPoint forwardPrimerSearcher( TmPoint tmPoint, int depth, int nucleotideNumber, double tempLimit  );
    
    /*select sequence calculator tmCounter*/
    public double tmPointNumerator( String seq );
    
    /*TmPoint object factory*/
    public TmPoint tmPointFactory( String seq );
    
    /*sequence calculator creator*/
//    public SequenceCalculatorInterface createCalculator();
    
}
