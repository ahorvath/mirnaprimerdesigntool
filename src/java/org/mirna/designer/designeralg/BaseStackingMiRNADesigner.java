package org.mirna.designer.designeralg;

import org.mirna.designer.interfaces.SequenceCalculatorInterface;

/**
 *
 * @author Attila
 */
public class BaseStackingMiRNADesigner extends BasicMiRNADesigner {
    
    public BaseStackingMiRNADesigner( int primerCollSize, String reversePrimer ) {
        
        super(primerCollSize, reversePrimer);
        
    }
    
    @Override
    public SequenceCalculatorInterface createCalculator() {
        
        return new BaseStackingSequenceCalculator();
        
    }

}
