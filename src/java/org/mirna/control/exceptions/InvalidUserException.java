package org.mirna.control.exceptions;

import java.io.Serializable;

/**
 *
 * @author Attila
 */
public class InvalidUserException extends Exception implements Serializable {
    
    public static final long serialVersionUID = 1L;    
    
    public InvalidUserException( String message ){
        super(message);
    }

}
