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

package org.mirna.servlets;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mirna.designer.bean.Userz;
import org.mirna.designer.logger.ActivityLogger;
import org.mirna.designer.logger.ExceptionLogger;
import org.mirna.bean.mirna.MiRNA;
import org.mirna.control.DataControl;

/**
 *
 * @author Attila
 */
public class MiRNANameSearch extends HttpServlet {
    
    public static final long serialVersionUID = 1L;
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            
            String miRNAName = request.getParameter( "miRNAName" );
            
            String genedb = (String) request.getSession().getAttribute( "genedb" );
            
            String miRNAStyle = (String) request.getSession().getAttribute( "miRNAStyle" );
            
            //TODO loggerMiRNANameSearch
            ActivityLogger.activityRequestWord(request, miRNAName, getClass().getName() );

            DataControl dataControl = new DataControl();

            Collection<MiRNA> miRNAColl = dataControl.loadMiRNAToName( genedb, miRNAStyle, miRNAName );
            
            int mSize = miRNAColl.size();
            
            //TODO loggerMiRNANameSearch
            ActivityLogger.activityResponseMiRNA(request, miRNAColl, getClass().getName() );            

            request.setAttribute( "miRNANumber", mSize );

            request.setAttribute( "miRNAColl", miRNAColl);
            
            request.setAttribute( "ptitle", genedb + " " + miRNAStyle + " miRNA(s)" + " (" + mSize + ")");

            getServletContext().getRequestDispatcher( "/index.jsp?action=genetomirna" ).forward( request, response );
            
        } catch( Exception ex) {
            //TODO loggerMiRNANameSearch
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
