/*
* The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection. The usergets the best result by two different Tm calculating methods.  The tool designs the miRNA specific sequence of the stem-loop RT primer as well. 
*
* copyright (C) 2009-2012 Astrid Research Ltd. 
* copyright (C) November, 2012 University of Debrecen, Clinical Genomic Center, Medical and Health Science Center, Debrecen, Hungary
*
* The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection.  The usergets the best result by two different Tm calculating methods.  The tool designs the miRNA specific sequence of the stem-loop RT primer as well. 
*
*    miRNA Design Tool is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    miRNA Design Tool is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with miRNA Primer Design Tool.  If not, see <http://www.gnu.org/licenses/>.
*/
/*
 */

package org.mirna.designer.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mirna.designer.bean.Oligo;
import org.mirna.designer.logger.ActivityLogger;
import org.mirna.designer.logger.ExceptionLogger;
import org.mirna.bean.mirna.MiRNA;
import org.mirna.control.DataControl;
import org.mirna.designer.designeralg.OligoDesigner;

/**
 *
 * @author Attila
 */
public class OligoDesign extends HttpServlet {
    
    public static final long serialVersionUID = 1L;
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            String[] upltag = request.getParameter( "upltag" ).split( ";" );
            
            String[] selectedMiRNAs = request.getParameterValues( "selectedMiRNA" );

            String genedb = (String) request.getSession().getAttribute( "genedb" );
            String miRNAStyle = (String) request.getSession().getAttribute( "miRNAStyle" );            
            
            //TODO loggerOligoDesign
            ActivityLogger.activityRequest( request, getClass().getName()  );
            
            DataControl dataControl = new DataControl();
            
            List<MiRNA> miRNAs = 
                    new ArrayList<MiRNA>( dataControl.loadMiRNAToIDs( genedb, miRNAStyle, selectedMiRNAs ) );
            
            //TODO loggerOligoDesigner
            ActivityLogger.activityRequestDesign(request, miRNAs, getClass().getName() );

            if( (selectedMiRNAs != null) && (selectedMiRNAs.length > 0) ) {

                Collection<Oligo> oligoColl = new OligoDesigner( /*selectedMiRNAs,*/genedb, miRNAs ).designOligo();
                
                //TODO OligoDesign
                ActivityLogger.activityResponseDesignMiRNA( request, oligoColl.size(), getClass().getName() );
                
                request.setAttribute( "ptitle", "Result of design" );
                
                request.setAttribute( "uplNo", upltag[0] );
                
                request.setAttribute( "uplSeq", upltag[1].toUpperCase() );

                request.setAttribute( "oligoColl", oligoColl );

                request.getRequestDispatcher( "/index.jsp?action=viewer" ).forward( request, response );

            } else {
                getServletContext().getRequestDispatcher( request.getContextPath() ).forward( request, response );
            }
            
        } catch( Exception ex) {
            //TODO loggerOligoDesign
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
