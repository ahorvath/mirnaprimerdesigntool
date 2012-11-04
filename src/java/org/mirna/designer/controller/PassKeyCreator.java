package org.mirna.designer.controller;

/**
 *
 * @author Attila
 */
public class PassKeyCreator {
    
    String msgc = new String("9e0c81f36a5d72b4");
    
    public PassKeyCreator() {}
    
    public String create() {
        
        StringBuffer result = new StringBuffer();
        
        int keyl = 1;
        
        double r = 0.0;
        
        while( (keyl < 2) && ((keyl % 2) != 0) ){
            r = Math.random();
            keyl = (int)( r * 16 );
        }        
        
        int msgn = 0;
        
        do{
            
            r = Math.random();
            msgn = (int)( r * 16 );
            
            if( msgn < 16 ){
                
                result.append( msgc.charAt(msgn) );
                
                --keyl;
                
            }
            
        }while( keyl > 0 );        
        
        return result.toString();
        
    }

}
