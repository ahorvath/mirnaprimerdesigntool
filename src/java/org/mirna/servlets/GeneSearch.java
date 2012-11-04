package org.mirna.servlets;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mirna.designer.logger.ActivityLogger;
import org.mirna.designer.logger.ExceptionLogger;
import org.mirna.bean.gene.Gene;
import org.mirna.bean.mirna.MiRNA;
import org.mirna.control.DataControl;

/**
 *
 * @author Attila
 */
public class GeneSearch extends HttpServlet {
    
    public static final long serialVersionUID = 1L;
    
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            String geneProperty = request.getParameter( "geneProperty" );
            
            if ( geneProperty.length() > 0 ) {
                
                String genedb = (String) request.getSession().getAttribute( "genedb" );
                
                String miRNAStyle = (String) request.getSession().getAttribute( "miRNAStyle" );
                
                //TODO loggerGeneSearch
                ActivityLogger.activityRequestWord( request, geneProperty, getClass().getName() );

                DataControl dataControl = new DataControl();

                Collection<Gene> geneColl = dataControl.loadGeneToName( genedb, miRNAStyle, geneProperty  );
                
                //TODO loggerGeneSearch
                ActivityLogger.activityResponseGene(request, geneColl, getClass().getName() );
                
                request.setAttribute( "geneColl", geneColl );
                
                request.setAttribute( "geneNumber", geneColl.size() );

                Collection<MiRNA> miRNAColl = dataControl.fromGeneCollToMiRNAs( geneColl );
                
                //TODO loggerGeneSearch
                ActivityLogger.activityResponseMiRNA(request, miRNAColl, getClass().getName() );
                
                int mSize = miRNAColl.size();
                
                request.setAttribute( "miRNAColl", miRNAColl);
                
                request.setAttribute( "ptitle", genedb + " " + miRNAStyle + " miRNA(s)" + " (" + mSize + ")");
                
                getServletContext().getRequestDispatcher( "/index.jsp?action=mirnatogene" ).forward( request, response );
                
            } else {
                getServletContext().getRequestDispatcher( "/index.jsp?action=startpage" ).forward( request, response );
            }
            
        } catch( Exception ex) {
            //TODO loggerGeneSearch
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
