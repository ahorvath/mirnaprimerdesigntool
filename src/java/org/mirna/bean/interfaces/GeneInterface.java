package org.mirna.bean.interfaces;

import java.util.Collection;
import org.mirna.bean.mirna.MiRNAInfo;

/**
 *
 * @author Attila
 */
public interface GeneInterface<T extends MiRNAInfo> {
    
    public Collection<T> getMiRNAInfoColl();

    public void setMiRNAInfoColl( Collection<T> miRNAInfoColl );    

}
