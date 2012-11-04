package org.mirna.bean.interfaces;

import org.mirna.bean.mirna.MiRNA;

/**
 *
 * @author Attila
 */
public interface MatureMiRNAInterface<T extends MiRNA> {
    
    public T getMiRNA();
    
    public void setMiRNA( T miRNA );

}
