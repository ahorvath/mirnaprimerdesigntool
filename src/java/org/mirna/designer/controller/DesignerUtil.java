package org.mirna.designer.controller;

/**
 *
 * @author Attila
 */
public class DesignerUtil {
    
    private DesignerUtil() {}
    
    public static String getFileExtension( String fileName ) {
        int dotPos = fileName.lastIndexOf(".");
        
        if( dotPos < 0 ) 
            return null;
        
        return fileName.substring( dotPos + 1 );
    }    

}
