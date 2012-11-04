package org.mirna.bean.interfaces;

import java.util.List;
import org.mirna.bean.mirna.MatureMiRNA;
import org.mirna.bean.mirna.MiRNAInfo;

/**
 *
 * @author Attila
 */
public interface MiRNAInterface<T extends MiRNAInfo, P extends MatureMiRNA> {
   
    public List<T> getMiRNAInfoColl();
    
    public void setMiRNAInfoColl( List<T> miRNAInfoColl );
    
    public List<P> getMatureMiRNAColl();
    
    public void setMatureMiRNAColl( List<P> matureMiRNAColl );

}
