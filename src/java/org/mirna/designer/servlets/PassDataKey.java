/*
 * miRNA Design Tool
 * 
 * password string key
 */

package org.mirna.designer.servlets;

import org.mirna.designer.controller.PassKeyCreator;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mirna.designer.bean.LoginProcess;
import org.mirna.designer.logger.LogInLogOutLogger;
import org.mirna.control.DataControl;

/**
 *
 * @author Attila
 */
public class PassDataKey extends HttpServlet {
    
    public static final long serialVersionUID = 1L;
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        
        String msg0 = new PassKeyCreator().create();
        String msg1 = new PassKeyCreator().create();
        
        try{

            String sessID = request.getSession().getId();

            LoginProcess loginProcess = new LoginProcess();
            loginProcess.setMsg0( msg0 );
            loginProcess.setMsg1( msg1 );
            loginProcess.setSessionID( sessID );
            loginProcess.setTimestamp( ts );

            DataControl dataControl = new DataControl();

            dataControl.persistData( loginProcess );
            
        } catch( Exception e ){
            msg0 = "Authentication error!";
        }
        
        //TODO loggerPassDataKey
        LogInLogOutLogger.auth( msg0, msg1, request.getRemoteAddr() );

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");

        PrintWriter out = response.getWriter();
        out.write("<?xml version='1.0' encoding='UTF-8'?>");
        out.write("<msg>");
        out.write("<msg0>" + msg0 + "</msg0>");
        out.write("<msg1>" + msg1 + "</msg1>");
        out.write("<sessid>" + request.getSession().getId() + "</sessid>");
        out.write("</msg>");        
        out.close();
            
        
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
