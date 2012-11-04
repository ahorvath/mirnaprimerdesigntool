package org.mirna.designer.filters;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import org.mirna.designer.controller.DesignerUtil;

/**
 *
 * @author Attila
 */
public class DatePointFileFilter implements FileFilter {
    
    private ArrayList<String> extensions = new ArrayList<String>();
    
    public DatePointFileFilter() {}
    
    public ArrayList<String> getExtensions() {
        return this.extensions;
    }

    @Override
    public boolean accept(File pathname) {
        
        String fileName = pathname.getName();
        String extension = DesignerUtil.getFileExtension( fileName );
        
        boolean valid = (!extension.equals("log")) && (fileName.contains("miRNADesigner"));
        
        if( valid )
            this.extensions.add( extension );
        
        return valid;
    }

}
