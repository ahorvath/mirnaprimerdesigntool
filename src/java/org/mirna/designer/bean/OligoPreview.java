package org.mirna.designer.bean;

/**
 *
 * @author Attila
 */
public class OligoPreview {
    
    private String miRNAPrefix;
    private String miRNAPostfix;
    private String cdnaPrefix;
    private String cdnaPostfix;
    
    public OligoPreview() {}

    public String getMiRNAPrefix() {
        return miRNAPrefix;
    }

    public void setMiRNAPrefix(String miRNAPrefix) {
        this.miRNAPrefix = miRNAPrefix;
    }

    public String getMiRNAPostfix() {
        return miRNAPostfix;
    }

    public void setMiRNAPostfix(String miRNAPostfix) {
        this.miRNAPostfix = miRNAPostfix;
    }

    public String getCdnaPrefix() {
        return cdnaPrefix;
    }

    public void setCdnaPrefix(String cdnaPrefix) {
        this.cdnaPrefix = cdnaPrefix;
    }

    public String getCdnaPostfix() {
        return cdnaPostfix;
    }

    public void setCdnaPostfix(String cdnaPostfix) {
        this.cdnaPostfix = cdnaPostfix;
    }
    
}
