package org.mirna.designer.filters;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author Attila
 */
public class FileNameFileFilter implements FileFilter {
    
    protected String fileName;
    
    public FileNameFileFilter( String fileName ) {
        this.fileName = fileName;
    }
    
    @Override
    public boolean accept(File pathname) {
        
        return pathname.getName().contains( this.fileName );
        
    }
    
}
