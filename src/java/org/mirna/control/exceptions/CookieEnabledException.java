package org.mirna.control.exceptions;

import javax.servlet.ServletException;

/**
 *
 * @author Attila
 */
public class CookieEnabledException extends ServletException {
    
    public static final long serialVersionUID = 1L;

    public CookieEnabledException() {
        super();
    }    
    
    public CookieEnabledException( String mess ) {
        super( mess );
    }

}
