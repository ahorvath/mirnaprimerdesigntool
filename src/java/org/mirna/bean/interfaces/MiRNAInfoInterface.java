package org.mirna.bean.interfaces;

import java.util.Collection;
import org.mirna.bean.gene.Gene;
import org.mirna.bean.mirna.MiRNA;

/**
 *
 * @author Attila
 */
public interface MiRNAInfoInterface<T extends MiRNA, P extends Gene> {
    
    public T getMiRNA();
    
    public void setMiRNA(T miRNA);
    
    public Collection<P> getGeneColl();
    
    public void setGeneColl( Collection<P> geneColl );

}
