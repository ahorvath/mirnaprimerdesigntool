/*
 * miRNA Primer Desig Tool
 */

package org.mirna.processor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mirna.designer.bean.Oligo;
import org.mirna.designer.bean.Userz;
import org.mirna.designer.logger.ActivityLogger;
import org.mirna.designer.logger.ExceptionLogger;
import org.mirna.designer.logger.LogInLogOutLogger;
import org.mirna.bean.gene.GeneGroup;
import org.mirna.control.GeneGroupReader;
import org.mirna.designer.designeralg.OwnOligoDesigner;
import org.mirna.designer.listeners.UserManagerListener;

/**
 *
 * @author Attila
 */
public class MainProcessor extends HttpServlet {
    
    public static final long serialVersionUID = 1L;
    
    private Object user;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try{

            String action = request.getParameter("action");
            
            String ip = request.getRemoteAddr();
            
            String usname = ((user = request.getSession().getAttribute( "user" )) != null) ? ((Userz)user).getUsname() : "unco";

            if( request.getParameter( "genedb" ) != null ) {
                request.getSession().setAttribute( "genedb", request.getParameter( "genedb" ) );
                request.getSession().setAttribute( "miRNAStyle", request.getParameter( "miRNAStyle" ) );
            }
            
            //TODO loggerMainProcessor
            ActivityLogger.activityRequest( request, getClass().getName() );
            
            if( action.equals( "logout" ) ){
                request.getSession().removeAttribute( "user" );
                
                //TODO loggerMainProcessor
                LogInLogOutLogger.logOut( usname, ip );
                
                //TODO loggedRemoveUser
                UserManagerListener.removeUser( request.getSession().getId() );
                
                request.getSession().invalidate();
                
                getServletContext().getRequestDispatcher( "/index.jsp?action=startpage" ).forward( request, response );                
                
            } /*else if( action.equals( "login" ) ){
                Boolean user = (Boolean) request.getSession().getAttribute("user");
                
                if( user ){
                    request.getRequestDispatcher( "/index.jsp?action=startpage" ).forward( request, response );
                    
                } else {
                    request.getSession().setAttribute( "user", false );
                    request.getRequestDispatcher( "/loginCheck" ).forward( request, response );
                }
                
            } */else if( action.equals( "mirnasearch" ) && !usname.equals("unco") ) {
                request.getRequestDispatcher("/search_mirna").forward( request, response );

            } else if( action.equals( "genesearch" ) && !usname.equals("unco") ) {
                request.getRequestDispatcher( "/search_gene" ).forward( request, response );
                
            } else if ( action.equals( "genegroup" ) && !usname.equals("unco") ) {

                InputStream groupIStream = this.getClass().getResourceAsStream( "../control/data/GeneGroup.txt" );

                Collection<GeneGroup> geneGroups = new GeneGroupReader( groupIStream ).getGeneGroups();

                request.setAttribute( "geneGroups", geneGroups );
                
                request.setAttribute( "ptitle", "Gene Groups Browser" );

                getServletContext().getRequestDispatcher( "/index.jsp?action=genegroup" ).forward( request, response );

            } else if ( action.equals( "genegroupsearch" ) && !usname.equals("unco") ) {
                request.getRequestDispatcher( "/ggrepresentation" ).forward( request, response );
            
            } else if( action.equals( "ownmirna" ) && !usname.equals("unco") ) {
                
                 //TODO loggerMainProcessor ownMiRNA
                 ActivityLogger.activityRequest( request, getClass().getName()  );
                 
                 String miRNASequence = request.getParameter( "ownMiRNASequence" );
                 
                 if( miRNASequence.length() > 0 ) {
                     
                     //TODO loggerMainProcessor ownMiRNA
                     ActivityLogger.activitiRequestOwnMiRNA( request, miRNASequence, getClass().getName() );

                     OwnOligoDesigner ownDesigner = new OwnOligoDesigner( usname, 5 );

                     Collection<Oligo> oligoColl = ownDesigner.ownDesign( miRNASequence );
                     
                     //TODO loggerMainProcessor ownMiRNA
                     ActivityLogger.activityResponseDesignMiRNA( request, oligoColl.size(), getClass().getName() );
                     
                     String[] upltag = request.getParameter( "upltag" ).split( ";" );
                     
                     request.setAttribute( "uplNo", upltag[0] );
                
                     request.setAttribute( "uplSeq", upltag[1].toUpperCase() );                     

                     request.setAttribute( "oligoColl", oligoColl );
                     
                     request.setAttribute( "genedb", null );
                     
                     request.getSession().setAttribute( "genedb", null );
                     request.getSession().setAttribute( "miRNAStyle", "Own" );

                     request.setAttribute( "ptitle", "Result of design" );

                     getServletContext().getRequestDispatcher( "/index.jsp?action=viewer" ).forward( request, response );
                 }

            } else if( action.equals( "design" ) && !usname.equals("unco") ) {
                request.getRequestDispatcher( "/design_oligo" ).forward( request, response );

            } else if( action.equals( "allmirna" ) && !usname.equals("unco") ) {
                request.getRequestDispatcher( "/representation" ).forward( request, response );

            } else {
                getServletContext().getRequestDispatcher( "/index.jsp?action=startpage" ).forward( request, response );
            }
                    
        } catch( Exception ex) {
            //TODO loggerMainProcessor
            ExceptionLogger.exceptionServletLog( request, getClass().getName(), ex.toString() );            
            throw new ServletException(ex);
        }

    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
