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
 * miRNA Design Tool
 *
 * registration control
 */
package org.mirna.designer.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mirna.designer.bean.Userz;
import org.mirna.designer.controller.MailService;
import org.mirna.designer.controller.AdminDataControl;
import org.mirna.designer.logger.ExceptionLogger;
import org.mirna.designer.logger.LogInLogOutLogger;

/**
 *
 * @author Attila
 */
public class Registration extends HttpServlet {

    public static final long serialVersionUID = 1L;
    static final String ADMIN_MAIL = "mirnadesigntool@astrid.hu";
    static final String MESSAGE = "Thank you for your registration request!"
            + "\n\nYour account is activated!"
            + "\n\nMore information about our company: http://www.astridbio.com";

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            Map params = request.getParameterMap();
            String username = ((String[]) params.get("reg_email"))[0];

            //TODO loggerLogInLogOutLogger
            LogInLogOutLogger.registration(username, "REQUEST", new Timestamp(System.currentTimeMillis()).toString());

            boolean exist = new AdminDataControl().isExistingUser(username);
            String reg_accept = exist ? "fail" : "ok";

            String usr_msg = "";

            String reg_msg1 = "";

            if (reg_accept.equals("ok")) {

                Userz usr = new Userz();
                usr.setUsname(username);
                usr.setPassMD5(((String[]) params.get("passwd_hash"))[0]);
                usr.setFirstname(((String[]) params.get("reg_first_name"))[0]);
                usr.setLastname(((String[]) params.get("reg_last_name"))[0]);
                usr.setComp_org(((String[]) params.get("reg_org"))[0]);
                usr.setCountry(((String[]) params.get("I_country"))[0]);
                usr.setUsertype("industry");
                usr.setTel("-");
                usr.setEmail(username);
                usr.setAdminrol(false);
                usr.setValid(true);

                Long usr_id = new AdminDataControl().addUser(usr);

                /*admin section ->*/
                usr_msg = "ID: " + usr_id
                        + "\n" + usr.toString();

                new MailService().sendMail(ADMIN_MAIL, usr_msg, "miRNA Primer Design Tool - Registration request");
                /*<- admin section*/

                /*user section ->*/
                usr_msg = MESSAGE
                        + "\n\nYour data:"
                        + "\n" + usr.toString()
                        + "\n\nYour password:\n[" + ((String[]) params.get("passwd"))[0] + "]\n\n";

                new MailService().sendMail(usr.getEmail(), usr_msg, "miRNA Primer Design Tool - Registration request");
                /*<- user section*/

                reg_msg1 = "The registration is succesful! Thank you for your registration! You will recieve your registration details in email soon!";

                //TODO loggerLogInLogOutLogger
                LogInLogOutLogger.registration(usr.getUsname(), "OK", new Timestamp(System.currentTimeMillis()).toString());

            } else if (reg_accept.equals("fail")) {

                reg_msg1 = "Username is not available!";

                //TODO loggerLogInLogOutLogger
                LogInLogOutLogger.registration(username, "FAIL", new Timestamp(System.currentTimeMillis()).toString());

            }

            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            PrintWriter out = response.getWriter();
            out.write("<?xml version='1.0' encoding='UTF-8'?>");
            out.write("<msg>");
            out.write("<msg0>" + reg_accept + "</msg0>");
            out.write("<msg1>" + reg_msg1 + "</msg1>");
            out.write("<sessid>" + request.getSession().getId() + "</sessid>");
            out.write("</msg>");
            out.close();

        } catch (Exception ex) {
            //TODO loggerExceptionLogger
            ExceptionLogger.exceptionServletLog(request, getClass().getName(), ex.toString());
            throw new ServletException(ex);
        }

    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
