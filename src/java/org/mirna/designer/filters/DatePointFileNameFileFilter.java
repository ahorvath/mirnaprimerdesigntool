package org.mirna.designer.filters;

import java.io.File;
import java.io.FileFilter;
import org.mirna.designer.controller.DesignerUtil;

/**
 *
 * @author Attila
 */
public class DatePointFileNameFileFilter extends FileNameFileFilter implements FileFilter {
    
    private String usname;
    
    private String udate;
    
    public DatePointFileNameFileFilter( String usname, String udate, String fileName ) {
        super( fileName );
        this.usname = usname;
        this.udate = udate.equals( "today" ) ? "log" : udate;
    }

    @Override
    public boolean accept(File pathname) {
        
        String fName = pathname.getName();
        String extension = DesignerUtil.getFileExtension( fName  );
        
        return ( extension.equals( this.udate ) && super.accept( pathname ) );
    }
    
}
